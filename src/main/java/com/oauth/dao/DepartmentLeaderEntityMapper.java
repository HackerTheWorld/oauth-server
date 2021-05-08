package com.oauth.dao;

import com.oauth.entity.DepartmentLeaderEntity;

public interface DepartmentLeaderEntityMapper {
    int deleteByPrimaryKey(Long departmentLeaderId);

    int insert(DepartmentLeaderEntity record);

    int insertSelective(DepartmentLeaderEntity record);

    DepartmentLeaderEntity selectByPrimaryKey(Long departmentLeaderId);

    int updateByPrimaryKeySelective(DepartmentLeaderEntity record);

    int updateByPrimaryKey(DepartmentLeaderEntity record);
}