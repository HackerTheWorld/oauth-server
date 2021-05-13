package com.oauth.dao;

import java.util.HashMap;
import java.util.List;

import com.oauth.entity.DepartmentEntity;
import com.oauth.tar.TreeTarget;
import com.oauth.vo.DepartmentVo;

@TreeTarget(treeTarget = "DepartmentTree")
public interface DepartmentEntityMapper extends TreeEntityMapper<DepartmentVo>{
    int deleteByPrimaryKey(Long departmentId);

    int insert(DepartmentEntity record);

    int insertSelective(DepartmentEntity record);

    DepartmentEntity selectByPrimaryKey(Long departmentId);

    int updateByPrimaryKeySelective(DepartmentEntity record);

    int updateByPrimaryKey(DepartmentEntity record);

    List<DepartmentEntity> selectParentByName(String parentDepartmentName,Long parentId);

    List<DepartmentVo> selectDepartment(HashMap<String,Object> param,List<Long> departmentList);
}