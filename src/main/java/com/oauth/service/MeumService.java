package com.oauth.service;

import java.util.List;

import com.oauth.vo.MeumVo;

import org.json.JSONObject;

public interface MeumService {
    
    void saveAndUpdateMeum(JSONObject jsonObject);

    List<MeumVo> selectMeum(Long meumId,String meumName,Long parentId,Long roleId,Integer needChild);
}
