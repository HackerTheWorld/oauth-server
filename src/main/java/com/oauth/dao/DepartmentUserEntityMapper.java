package com.oauth.dao;

import com.oauth.entity.DepartmentUserEntity;
import com.oauth.tar.RelationshipTarget;
import com.oauth.tar.RelationshipMapper;
@RelationshipTarget(relationshipTargetName = "DepartmentUser")
public interface DepartmentUserEntityMapper extends RelationshipMapper<DepartmentUserEntity>{
    int deleteByPrimaryKey(Long departmentUserId);

    int insert(DepartmentUserEntity record);
    @Override
    int insertSelective(DepartmentUserEntity record);

    DepartmentUserEntity selectByPrimaryKey(Long departmentUserId);
    @Override
    int updateByPrimaryKeySelective(DepartmentUserEntity record);

    int updateByPrimaryKey(DepartmentUserEntity record);
}