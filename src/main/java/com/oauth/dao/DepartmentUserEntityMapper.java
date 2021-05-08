package com.oauth.dao;

import com.oauth.entity.DepartmentUserEntity;

public interface DepartmentUserEntityMapper {
    int deleteByPrimaryKey(Long departmentUserId);

    int insert(DepartmentUserEntity record);

    int insertSelective(DepartmentUserEntity record);

    DepartmentUserEntity selectByPrimaryKey(Long departmentUserId);

    int updateByPrimaryKeySelective(DepartmentUserEntity record);

    int updateByPrimaryKey(DepartmentUserEntity record);
}