package com.oauth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountExpiredException;

import com.oauth.contons.MessageConstant;
import com.oauth.converter.UserConverter;
import com.oauth.dao.DepartmentUserEntityMapper;
import com.oauth.dao.ParentUserEntityMapper;
import com.oauth.dao.PostUserEntityMapper;
import com.oauth.dao.UserInforEntityMapper;
import com.oauth.entity.DepartmentUserEntity;
import com.oauth.entity.ParentUserEntity;
import com.oauth.entity.PostUserEntity;
import com.oauth.entity.UserInforEntity;
import com.oauth.service.UserService;
import com.oauth.vo.User;
import com.oauth.vo.UserInforVo;
import com.oauth.vo.UserPrincipal;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
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
  private DepartmentUserEntityMapper departmentUserEntityMapper;
  @Autowired
  private PostUserEntityMapper postUserEntityMapper;
  @Autowired
  private ParentUserEntityMapper parentUserEntityMapper;
  @Autowired
  private PasswordEncoder passwordEncoder;

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
    Long userId = jsonObject.optLong("userId", 0);
    String username = jsonObject.optString("username", "");
    String password = jsonObject.optString("password", "");
    UserInforEntity userInforEntity = new UserInforEntity();
    if (StringUtils.isBlank(username) && userId == 0) {
      throw new Exception("用户名不能为空");
    } else {
      if (userId == 0 && !CollectionUtils.isEmpty(userEntityMapper.selectByUsername(username, null))) {
        throw new Exception("用户名已存在");
      }
    }
    if (StringUtils.isBlank(password) && userId == 0) {
      throw new Exception("密码不能为空");
    }
    String email = jsonObject.optString("email", "");
    String realname = jsonObject.optString("realname", "");
    String phone = jsonObject.optString("phone", "");
    Integer status = jsonObject.optInt("status", 1);

    userInforEntity.setUsername(username);
    userInforEntity.setPassword(passwordEncoder.encode(password));
    userInforEntity.setEmail(email);
    userInforEntity.setRealname(realname);
    userInforEntity.setPhone(phone);
    userInforEntity.setStatus(status);
    if (userId == 0) {
      userEntityMapper.insertSelective(userInforEntity);
    } else {
      userInforEntity.setUserId(userId);
      userEntityMapper.updateByPrimaryKeySelective(userInforEntity);
    }

    department(jsonObject.optJSONArray("department"), userInforEntity.getUserId());
    post(jsonObject.optJSONArray("post"), userInforEntity.getUserId());
    parentUser(jsonObject.optJSONArray("parent"),userInforEntity.getUserId());

  }

  private void department(JSONArray departmentArray, Long userId){
    if (departmentArray != null && !departmentArray.isEmpty()) {
      for (int i = 0; i < departmentArray.length(); i++) {
        JSONObject jsonObject = departmentArray.optJSONObject(i);
        Long departmentUserId = jsonObject.optLong("departmentUserId", 0);
        Long departmentId = jsonObject.optLong("departmentId", 0);
        DepartmentUserEntity departmentUserEntity = new DepartmentUserEntity();
        departmentUserEntity.setUserId(userId);
        departmentUserEntity.setDepartmentId(departmentId);
        if (departmentUserId == 0) {
          departmentUserEntityMapper.insertSelective(departmentUserEntity);
        } else {
          departmentUserEntity.setDepartmentUserId(departmentUserId);
          departmentUserEntityMapper.updateByPrimaryKeySelective(departmentUserEntity);
        }
      }
    }
  }

  // private void role(JSONArray roleArray, Long userId){
  //   if (roleArray != null && !roleArray.isEmpty()) {
  //     for (int i = 0; i < roleArray.length(); i++) {
  //       JSONObject jsonObject = roleArray.optJSONObject(i);
  //       Long userRoleId = jsonObject.optLong("userRoleId", 0);
  //       Long roleId = jsonObject.optLong("roleId", 0);
  //        departmentUserEntity = new DepartmentUserEntity();
  //       departmentUserEntity.setUserId(userId);
  //       departmentUserEntity.setDepartmentId(departmentId);
  //       if (departmentUserId == 0) {
  //         departmentUserEntityMapper.insertSelective(departmentUserEntity);
  //       } else {
  //         departmentUserEntity.setDepartmentUserId(departmentUserId);
  //         departmentUserEntityMapper.updateByPrimaryKeySelective(departmentUserEntity);
  //       }
  //     }
  //   }
  // }

  private void parentUser(JSONArray parentUserArray, Long userId) {
    if (parentUserArray != null && !parentUserArray.isEmpty()) {
      for (int i = 0; i < parentUserArray.length(); i++) {
        JSONObject jsonObject = parentUserArray.optJSONObject(i);
        Long parentUserId = jsonObject.optLong("parentUserId", 0);
        Long parentId = jsonObject.optLong("userParentId", 0);
        ParentUserEntity parentUserEntity = new ParentUserEntity();
        parentUserEntity.setUserId(userId);
        parentUserEntity.setUserParentId(parentId);
        if (parentUserId == 0) {
          parentUserEntityMapper.insertSelective(parentUserEntity);
        } else {
          parentUserEntity.setParentUserId(parentUserId);
          parentUserEntityMapper.updateByPrimaryKeySelective(parentUserEntity);
        }
      }
    }
  }

  private void post(JSONArray postArray, Long userId) {
    if (postArray != null && !postArray.isEmpty()) {
      for (int i = 0; i < postArray.length(); i++) {
        JSONObject jsonObject = postArray.optJSONObject(i);
        Long postUserId = jsonObject.optLong("postUserId", 0);
        Long postId = jsonObject.optLong("postId", 0);
        PostUserEntity postUserEntity = new PostUserEntity();
        postUserEntity.setPostId(postId);
        postUserEntity.setUserId(userId);
        if (postUserId == 0) {
          postUserEntityMapper.insertSelective(postUserEntity);
        } else {
          postUserEntity.setPostUserId(postUserId);
          postUserEntityMapper.updateByPrimaryKeySelective(postUserEntity);
        }
      }
    }
  }

  @Override
  public List<UserInforVo> selectUserInfor(Integer status, String username, Long userId, String realname,Long parentUserId, String parentRealname, String email,
      String phone, Long departmentId, String department, Long postId, String post, String postCode) {
    boolean needUserId = false;
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
    if(parentUserId != null && StringUtils.isNotBlank(parentRealname)){
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
    if(needUserId && CollectionUtils.isEmpty(userIdList)){
      return new ArrayList<>();
    }
    HashMap<String,Object> param = new HashMap<String,Object>();
    param.put("status", status);
    param.put("username", username);
    param.put("userId", userId);
    param.put("realname", realname);
    param.put("phone", phone);
    param.put("email", email);

    List<UserInforVo> userInforVos = userEntityMapper.selectUserInforVos(param,userIdList);
    for(UserInforVo userInforVo : userInforVos){
      userInforVo.setDepartmentUser(userInforVo.getDepartmentUser().stream().filter(predicate -> predicate.getDepartmentId() != null).collect(Collectors.toList()));
      userInforVo.setParent(userInforVo.getParent().stream().filter(predicate -> predicate.getUserId() != null).collect(Collectors.toList()));
    }
    return userInforVos;
  }

}