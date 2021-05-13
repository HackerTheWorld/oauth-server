package com.oauth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountExpiredException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.comon.RelationshipUtil;
import com.oauth.comon.TreeUtil;
import com.oauth.contons.MessageConstant;
import com.oauth.converter.UserConverter;
import com.oauth.dao.MeumEntityMapper;
import com.oauth.dao.RelationshipMapper;
import com.oauth.dao.TreeEntityMapper;
import com.oauth.dao.UserInforEntityMapper;
import com.oauth.entity.DepartmentUserEntity;
import com.oauth.entity.ParentUserEntity;
import com.oauth.entity.PostUserEntity;
import com.oauth.entity.UserInforEntity;
import com.oauth.service.UserService;
import com.oauth.tar.RelationshipTarget;
import com.oauth.tar.TreeTarget;
import com.oauth.vo.User;
import com.oauth.vo.UserInforVo;
import com.oauth.vo.UserPrincipal;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserInforEntityMapper userEntityMapper;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private MeumEntityMapper meumEntityMapper;

  private final Map<String, RelationshipMapper> relaMap;

  private final Map<String, TreeEntityMapper> treeMap;

  @Autowired
  public UserServiceImpl(List<RelationshipMapper> relaMap, List<TreeEntityMapper> treeMap) {

    this.relaMap = relaMap.stream()
        .collect(Collectors.toMap(sfcInter -> Objects
            .requireNonNull(AnnotationUtils.findAnnotation(sfcInter.getClass(), RelationshipTarget.class))
            .relationshipTargetName(), v -> v, (v1, v2) -> v1));

    this.treeMap = treeMap.stream()
        .collect(
            Collectors.toMap(
                sfcInter -> Objects
                    .requireNonNull(AnnotationUtils.findAnnotation(sfcInter.getClass(), TreeTarget.class)).treeTarget(),
                v -> v, (v1, v2) -> v1));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserPrincipal userPrincipal = new UserPrincipal(findUsers(username));

    if (!userPrincipal.isEnabled()) {
      throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
    } else if (!userPrincipal.isAccountNonLocked()) {
      throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
    } else if (!userPrincipal.isAccountNonExpired()) {
      try {
        throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
      } catch (AccountExpiredException e) {
        e.printStackTrace();
      }
    } else if (!userPrincipal.isCredentialsNonExpired()) {
      throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
    }
    return userPrincipal;
  }

  private User findUsers(String username) {
    User user = new User();
    UserInforEntity userEntity = userEntityMapper.selectByUsername(username, null).get(0);
    UserConverter userConverter = new UserConverter();
    BeanCopier bCopier = BeanCopier.create(UserInforEntity.class, User.class, true);
    bCopier.copy(userEntity, user, userConverter);
    user.setId(userEntity.getUserId());
    return user;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveAndUpdateUserInfor(JSONObject jsonObject) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    UserInforEntity userInforEntity = objectMapper.convertValue(jsonObject, UserInforEntity.class);
    boolean isNew = userInforEntity.getUserId() == null || userInforEntity.getUserId() == 0;
    if (StringUtils.isBlank(userInforEntity.getUsername()) && isNew) {
      throw new Exception("用户名不能为空");
    } else {
      if (isNew && !CollectionUtils.isEmpty(userEntityMapper.selectByUsername(userInforEntity.getUsername(), null))) {
        throw new Exception("用户名已存在");
      }
    }
    if (StringUtils.isBlank(userInforEntity.getPassword()) && isNew) {
      throw new Exception("密码不能为空");
    }

    userInforEntity.setPassword(passwordEncoder.encode(userInforEntity.getPassword()));
    if (isNew) {
      userEntityMapper.insertSelective(userInforEntity);
    } else {
      userEntityMapper.updateByPrimaryKeySelective(userInforEntity);
    }
    RelationshipUtil.relationship(jsonObject.optJSONArray("department"), "userId", userInforEntity.getUserId(),
        DepartmentUserEntity.class, relaMap.get("DepartmentUser"));
    RelationshipUtil.relationship(jsonObject.optJSONArray("post"), "userId", userInforEntity.getUserId(),
        PostUserEntity.class, relaMap.get("PostUser"));
    RelationshipUtil.relationship(jsonObject.optJSONArray("parent"), "userId", userInforEntity.getUserId(),
        ParentUserEntity.class, relaMap.get("ParentUser"));
    RelationshipUtil.relationship(jsonObject.optJSONArray("role"), "userId", userInforEntity.getUserId(),
        PostUserEntity.class, relaMap.get("PostUser"));

  }

  @Override
  public List<UserInforVo> selectUserInfor(Integer status, String username, Long userId, String realname,
      Long parentUserId, String parentRealname, String email, String phone, Long departmentId, String department,
      Long postId, String post, String postCode, Integer needChild) {
    boolean needUserId = false;

    Consumer<List<UserInforVo>> distinctVo = (list) -> {
      for (UserInforVo userInforVoEle : list) {
        userInforVoEle.setDepartmentUser(userInforVoEle.getDepartmentUser().stream()
            .filter(predicate -> predicate.getDepartmentId() != null).distinct().collect(Collectors.toSet()));
        userInforVoEle.setParent(userInforVoEle.getParent().stream().filter(predicate -> predicate.getUserId() != null)
            .distinct().collect(Collectors.toSet()));
        userInforVoEle.setPostEntity(userInforVoEle.getPostEntity().stream()
            .filter(predicate -> predicate.getPostId() != null).distinct().collect(Collectors.toSet()));
        userInforVoEle.setRoleEntity(userInforVoEle.getRoleEntity().stream()
            .filter(predicate -> predicate.getRoleId() != null).distinct().collect(Collectors.toSet()));
        userInforVoEle.getRoleEntity().forEach(action -> userInforVoEle.setMeumEntity(
            meumEntityMapper.selectMeumByRoleId(action.getRoleId()).stream().collect(Collectors.toSet())));
      }
    };

    BiConsumer<Set<UserInforVo>, UserInforVo> setChildren = (list, userVo) -> {
      userVo.setChildren(list);
    };

    List<Long> userIdList = new ArrayList<Long>();
    if (postId != null || StringUtils.isNotBlank(post) || StringUtils.isNotBlank(postCode)) {
      needUserId = true;
      userIdList.addAll(userEntityMapper.selectPostUserByName(postId, post, postCode));
    }
    if (departmentId != null || StringUtils.isNotBlank(department)) {
      List<Long> departmeLongs = userEntityMapper.selectDepartmentUserByName(departmentId, department);
      if (needUserId) {
        userIdList.retainAll(departmeLongs);
      } else {
        userIdList.addAll(departmeLongs);
      }
      needUserId = true;
    }
    if (parentUserId != null && StringUtils.isNotBlank(parentRealname)) {
      List<Long> parentList = userEntityMapper.selectParentUserByName(parentUserId, parentRealname);
      if (needUserId) {
        userIdList.retainAll(parentList);
      } else {
        userIdList.addAll(parentList);
      }
      needUserId = true;
    }
    if (userId != null) {
      List<Long> depList = new ArrayList<Long>();
      depList.add(userId);
      if (needUserId) {
        userIdList.retainAll(depList);
      } else {
        userIdList.addAll(depList);
      }
      needUserId = true;
    }
    if (needUserId && CollectionUtils.isEmpty(userIdList)) {
      return new ArrayList<>();
    }
    HashMap<String, Object> param = new HashMap<String, Object>();
    param.put("status", status);
    param.put("username", username);
    param.put("userId", userId);
    param.put("realname", realname);
    param.put("phone", phone);
    param.put("email", email);

    List<UserInforVo> userInforVos = userEntityMapper.selectUserInforVos(param, userIdList);
    distinctVo.accept(userInforVos);

    if (needChild == 1) {
      return userInforVos;
    } else {
      TreeUtil.traversalTree(userInforVos, needChild, UserInforVo.class, treeMap.get("UserInforTree"), distinctVo,
          setChildren);
    }

    return userInforVos;
  }

}