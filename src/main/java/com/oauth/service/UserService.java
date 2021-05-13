package com.oauth.service;

import java.util.List;

import com.oauth.vo.UserInforVo;

import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    
    void saveAndUpdateUserInfor(JSONObject jsonObject) throws Exception;

    List<UserInforVo> selectUserInfor(Integer status,String username,Long userId,String realname,Long parentId,String parentRealname,String email,String phone,Long departmentId,String department,Long postId,String post,String postCode,Integer needChild);
}
