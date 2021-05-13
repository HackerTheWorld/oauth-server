package com.oauth.dao;

import com.oauth.entity.PostUserEntity;
import com.oauth.tar.RelationshipTarget;
@RelationshipTarget(relationshipTargetName = "PostUser")
public interface PostUserEntityMapper extends RelationshipMapper<PostUserEntity> {
    int deleteByPrimaryKey(Long postUserId);

    int insert(PostUserEntity record);
    @Override
    int insertSelective(PostUserEntity record);

    PostUserEntity selectByPrimaryKey(Long postUserId);
    @Override
    int updateByPrimaryKeySelective(PostUserEntity record);

    int updateByPrimaryKey(PostUserEntity record);
}