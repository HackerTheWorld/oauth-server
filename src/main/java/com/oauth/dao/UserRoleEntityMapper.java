package com.oauth.dao;

import com.oauth.entity.UserRoleEntity;
import com.oauth.tar.RelationshipTarget;
import com.oauth.tar.RelationshipMapper;
@RelationshipTarget(relationshipTargetName = "UserRole")
public interface UserRoleEntityMapper extends RelationshipMapper<UserRoleEntity>{
    int deleteByPrimaryKey(Long userRoleId);

    int insert(UserRoleEntity record);

    @Override
    int insertSelective(UserRoleEntity record);

    UserRoleEntity selectByPrimaryKey(Long userRoleId);

    @Override
    int updateByPrimaryKeySelective(UserRoleEntity record);

    int updateByPrimaryKey(UserRoleEntity record);
}