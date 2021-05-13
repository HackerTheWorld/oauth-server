package com.oauth.service;

import java.util.List;

import com.oauth.vo.RoleVo;

import org.json.JSONObject;

public interface RoleService {
    
    void saveAndUpdateRole(JSONObject jsonObject) throws Exception;

    List<RoleVo> selectRole(Long roleId,String roleName);
}
