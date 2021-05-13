package com.oauth.dao;

import com.oauth.entity.ParentUserEntity;
import com.oauth.tar.RelationshipTarget;
@RelationshipTarget(relationshipTargetName = "ParentUser")
public interface ParentUserEntityMapper extends RelationshipMapper<ParentUserEntity>{
    int deleteByPrimaryKey(Long parentUserId);

    int insert(ParentUserEntity record);
    @Override
    int insertSelective(ParentUserEntity record);

    ParentUserEntity selectByPrimaryKey(Long parentUserId);
    @Override
    int updateByPrimaryKeySelective(ParentUserEntity record);

    int updateByPrimaryKey(ParentUserEntity record);
}