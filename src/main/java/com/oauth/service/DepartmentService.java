package com.oauth.service;

import java.util.List;

import com.oauth.receiving.DepartmentRece;
import com.oauth.vo.DepartmentVo;

public interface DepartmentService {
    void saveAndUpdateDepartment(DepartmentRece departmentRece) throws Exception;

    List<DepartmentVo> selectDepartment(Integer status,String departmentName,Long departmentId,Long parentId,String parentName,Long userId,String  username,Integer needChild);
}
