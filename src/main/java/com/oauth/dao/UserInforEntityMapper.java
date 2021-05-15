package com.oauth.dao;

import java.util.List;

import com.oauth.entity.UserInforEntity;
public interface UserInforEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInforEntity record);

    int insertSelective(UserInforEntity record);

    UserInforEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInforEntity record);

    int updateByPrimaryKey(UserInforEntity record);

    List<UserInforEntity> selectByUsername(String username,Long userId);

}