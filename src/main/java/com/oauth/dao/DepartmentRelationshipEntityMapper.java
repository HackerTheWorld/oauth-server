package com.oauth.dao;

import com.oauth.entity.DepartmentRelationshipEntity;
import com.oauth.tar.RelationshipTar;
@RelationshipTar(relationshipTarName = "DepartmentRelationship")
public interface DepartmentRelationshipEntityMapper extends RelationshipMapper<DepartmentRelationshipEntity>{
    int deleteByPrimaryKey(Long departmentRelationshipId);

    int insert(DepartmentRelationshipEntity record);

    int insertSelective(DepartmentRelationshipEntity record);

    DepartmentRelationshipEntity selectByPrimaryKey(Long departmentRelationshipId);

    int updateByPrimaryKeySelective(DepartmentRelationshipEntity record);

    int updateByPrimaryKeyWithBLOBs(DepartmentRelationshipEntity record);

    int updateByPrimaryKey(DepartmentRelationshipEntity record);
}