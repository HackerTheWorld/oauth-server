package com.oauth.dao;

import com.oauth.entity.UserClientEntity;

public interface UserClientEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserClientEntity record);

    int insertSelective(UserClientEntity record);

    UserClientEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserClientEntity record);

    int updateByPrimaryKey(UserClientEntity record);
}