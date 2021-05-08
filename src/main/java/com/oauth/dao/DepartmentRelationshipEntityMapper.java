package com.oauth.dao;

import com.oauth.entity.DepartmentRelationshipEntity;

public interface DepartmentRelationshipEntityMapper {
    int deleteByPrimaryKey(Long departmentRelationshipId);

    int insert(DepartmentRelationshipEntity record);

    int insertSelective(DepartmentRelationshipEntity record);

    DepartmentRelationshipEntity selectByPrimaryKey(Long departmentRelationshipId);

    int updateByPrimaryKeySelective(DepartmentRelationshipEntity record);

    int updateByPrimaryKeyWithBLOBs(DepartmentRelationshipEntity record);

    int updateByPrimaryKey(DepartmentRelationshipEntity record);
}