package com.oauth.dao;

import com.oauth.entity.ParentUserEntity;

public interface ParentUserEntityMapper {
    int deleteByPrimaryKey(Long parentUserId);

    int insert(ParentUserEntity record);

    int insertSelective(ParentUserEntity record);

    ParentUserEntity selectByPrimaryKey(Long parentUserId);

    int updateByPrimaryKeySelective(ParentUserEntity record);

    int updateByPrimaryKey(ParentUserEntity record);
}