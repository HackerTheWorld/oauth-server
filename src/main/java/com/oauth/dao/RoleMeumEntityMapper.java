package com.oauth.dao;

import com.oauth.entity.RoleMeumEntity;
import com.oauth.tar.RelationshipTarget;

@RelationshipTarget(relationshipTargetName = "RoleMeum")
public interface RoleMeumEntityMapper  extends RelationshipMapper<RoleMeumEntity>{
    int deleteByPrimaryKey(Long roleMeumId);

    int insert(RoleMeumEntity record);
    
    @Override
    int insertSelective(RoleMeumEntity record);

    RoleMeumEntity selectByPrimaryKey(Long roleMeumId);

    @Override
    int updateByPrimaryKeySelective(RoleMeumEntity record);

    int updateByPrimaryKey(RoleMeumEntity record);
}