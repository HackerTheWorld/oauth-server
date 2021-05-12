package com.oauth.dao;

import com.oauth.entity.DepartmentUserEntity;
import com.oauth.tar.RelationshipTar;
@RelationshipTar(relationshipTarName = "DepartmentUser")
public interface DepartmentUserEntityMapper extends RelationshipMapper<DepartmentUserEntity>{
    int deleteByPrimaryKey(Long departmentUserId);

    int insert(DepartmentUserEntity record);

    int insertSelective(DepartmentUserEntity record);

    DepartmentUserEntity selectByPrimaryKey(Long departmentUserId);

    int updateByPrimaryKeySelective(DepartmentUserEntity record);

    int updateByPrimaryKey(DepartmentUserEntity record);
}