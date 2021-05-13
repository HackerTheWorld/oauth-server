package com.oauth.service;

import java.util.List;

import com.oauth.receiving.RoleRece;
import com.oauth.vo.RoleVo;

public interface RoleService {
    
    void saveAndUpdateRole(RoleRece roleRece) throws Exception;

    List<RoleVo> selectRole(Long roleId,String roleName);
}
