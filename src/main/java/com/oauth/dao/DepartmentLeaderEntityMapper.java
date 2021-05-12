package com.oauth.dao;

import com.oauth.entity.DepartmentLeaderEntity;
import com.oauth.tar.RelationshipTar;
@RelationshipTar(relationshipTarName = "DepartmentLeader")
public interface DepartmentLeaderEntityMapper extends RelationshipMapper<DepartmentLeaderEntity> {
    int deleteByPrimaryKey(Long departmentLeaderId);

    int insert(DepartmentLeaderEntity record);

    int insertSelective(DepartmentLeaderEntity record);

    DepartmentLeaderEntity selectByPrimaryKey(Long departmentLeaderId);

    int updateByPrimaryKeySelective(DepartmentLeaderEntity record);

    int updateByPrimaryKey(DepartmentLeaderEntity record);
}