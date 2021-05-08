package com.oauth.service;

import java.util.List;

import com.oauth.vo.DepartmentVo;

import org.json.JSONObject;

public interface DepartmentService {
    void saveAndUpdateDepartment(JSONObject jsonObject) throws Exception;

    List<DepartmentVo> selectDepartment(Integer status,String departmentName,Long departmentId,Long parentId,String parentName,Long userId,String  username,Integer needChild);
}
