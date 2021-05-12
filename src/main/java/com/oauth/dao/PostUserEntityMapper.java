package com.oauth.dao;

import com.oauth.entity.PostUserEntity;
import com.oauth.tar.RelationshipTar;
@RelationshipTar(relationshipTarName = "PostUser")
public interface PostUserEntityMapper extends RelationshipMapper<PostUserEntity> {
    int deleteByPrimaryKey(Long postUserId);

    int insert(PostUserEntity record);

    int insertSelective(PostUserEntity record);

    PostUserEntity selectByPrimaryKey(Long postUserId);

    int updateByPrimaryKeySelective(PostUserEntity record);

    int updateByPrimaryKey(PostUserEntity record);
}