package com.oauth.service;

import com.oauth.vo.MeumVo;

import org.json.JSONObject;

public interface MeumService {
    
    void saveAndUpdateMeum(JSONObject jsonObject);
    
    MeumVo selectMeum(Long meumId,String meumName,Long parentId,String parentMeumName,Integer needChild);
}
