package com.oauth.dao;

import com.oauth.entity.PostEntity;

public interface PostEntityMapper {
    int deleteByPrimaryKey(Long postId);

    int insert(PostEntity record);

    int insertSelective(PostEntity record);

    PostEntity selectByPrimaryKey(Long postId);

    int updateByPrimaryKeySelective(PostEntity record);

    int updateByPrimaryKey(PostEntity record);
}