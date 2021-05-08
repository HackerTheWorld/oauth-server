package com.oauth.dao;

import com.oauth.entity.PostUserEntity;

public interface PostUserEntityMapper {
    int deleteByPrimaryKey(Long postUserId);

    int insert(PostUserEntity record);

    int insertSelective(PostUserEntity record);

    PostUserEntity selectByPrimaryKey(Long postUserId);

    int updateByPrimaryKeySelective(PostUserEntity record);

    int updateByPrimaryKey(PostUserEntity record);
}