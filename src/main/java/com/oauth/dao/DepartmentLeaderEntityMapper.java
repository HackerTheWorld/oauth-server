package com.oauth.dao;

import com.oauth.entity.DepartmentLeaderEntity;
import com.oauth.tar.RelationshipTarget;
import com.oauth.tar.RelationshipMapper;
@RelationshipTarget(relationshipTargetName = "DepartmentLeader")
public interface DepartmentLeaderEntityMapper extends RelationshipMapper<DepartmentLeaderEntity> {
    int deleteByPrimaryKey(Long departmentLeaderId);

    int insert(DepartmentLeaderEntity record);
    @Override
    int insertSelective(DepartmentLeaderEntity record);

    DepartmentLeaderEntity selectByPrimaryKey(Long departmentLeaderId);
    @Override
    int updateByPrimaryKeySelective(DepartmentLeaderEntity record);

    int updateByPrimaryKey(DepartmentLeaderEntity record);
}