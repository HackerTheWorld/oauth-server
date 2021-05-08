package com.oauth.service;

import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    
    void saveAndUpdateUserInfor(JSONObject jsonObject) throws Exception;

}
