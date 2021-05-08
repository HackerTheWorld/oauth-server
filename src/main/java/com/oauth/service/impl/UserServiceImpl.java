package com.oauth.service.impl;

import javax.security.auth.login.AccountExpiredException;

import com.oauth.contons.MessageConstant;
import com.oauth.converter.UserConverter;
import com.oauth.dao.UserInforEntityMapper;
import com.oauth.entity.UserInforEntity;
import com.oauth.service.UserService;
import com.oauth.vo.User;
import com.oauth.vo.UserPrincipal;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserInforEntityMapper userEntityMapper;

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
    UserInforEntity userEntity = userEntityMapper.selectByUsername(username,null).get(0);
    UserConverter userConverter = new UserConverter();
    BeanCopier bCopier = BeanCopier.create(UserInforEntity.class, User.class, true);
    bCopier.copy(userEntity, user, userConverter);
    user.setId(userEntity.getUserId());
    return user;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveAndUpdateUserInfor(JSONObject jsonObject) throws Exception {
    Long userId = jsonObject.optLong("userId",0);
    String username = jsonObject.optString("username","");
    String password = jsonObject.optString("password","");
    if(StringUtils.isBlank(username) && userId == -1){
      throw new Exception("用户名不能为空");
    }else{
      if(userId == -1 && !CollectionUtils.isEmpty(userEntityMapper.selectByUsername(username,null))){
        throw new Exception("用户名已存在");
      }
    }
    if(StringUtils.isBlank(password) && userId == -1 ){
      throw new Exception("密码不能为空");
    }
    
    
    
  }

}