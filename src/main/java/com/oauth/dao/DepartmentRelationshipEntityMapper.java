package com.oauth.dao;

import com.oauth.entity.DepartmentRelationshipEntity;
import com.oauth.tar.RelationshipTarget;
import com.oauth.tar.RelationshipMapper;
@RelationshipTarget(relationshipTargetName = "DepartmentRelationship")
public interface DepartmentRelationshipEntityMapper extends RelationshipMapper<DepartmentRelationshipEntity>{
    int deleteByPrimaryKey(Long departmentRelationshipId);

    int insert(DepartmentRelationshipEntity record);
    @Override
    int insertSelective(DepartmentRelationshipEntity record);

    DepartmentRelationshipEntity selectByPrimaryKey(Long departmentRelationshipId);
    @Override
    int updateByPrimaryKeySelective(DepartmentRelationshipEntity record);

    int updateByPrimaryKey(DepartmentRelationshipEntity record);
}