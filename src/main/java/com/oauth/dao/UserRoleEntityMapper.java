package com.oauth.dao;

import com.oauth.entity.UserRoleEntity;
import com.oauth.tar.RelationshipTar;

@RelationshipTar(relationshipTarName = "UserRole")
public interface UserRoleEntityMapper extends RelationshipMapper<UserRoleEntity>{
    int deleteByPrimaryKey(Long userRoleId);

    int insert(UserRoleEntity record);

    int insertSelective(UserRoleEntity record);

    UserRoleEntity selectByPrimaryKey(Long userRoleId);

    int updateByPrimaryKeySelective(UserRoleEntity record);

    int updateByPrimaryKey(UserRoleEntity record);
}