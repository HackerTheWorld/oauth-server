package com.oauth.dao;

import com.oauth.entity.ParentUserEntity;
import com.oauth.tar.RelationshipTar;
@RelationshipTar(relationshipTarName = "ParentUser")
public interface ParentUserEntityMapper extends RelationshipMapper<ParentUserEntity>{
    int deleteByPrimaryKey(Long parentUserId);

    int insert(ParentUserEntity record);

    int insertSelective(ParentUserEntity record);

    ParentUserEntity selectByPrimaryKey(Long parentUserId);

    int updateByPrimaryKeySelective(ParentUserEntity record);

    int updateByPrimaryKey(ParentUserEntity record);
}