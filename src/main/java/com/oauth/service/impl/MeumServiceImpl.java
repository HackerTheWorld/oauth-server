package com.oauth.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.dao.MeumEntityMapper;
import com.oauth.entity.MeumEntityWithBLOBs;
import com.oauth.service.MeumService;
import com.oauth.vo.MeumVo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.v3.oas.annotations.servers.Server;

@Server
public class MeumServiceImpl implements MeumService{

    @Autowired
    private MeumEntityMapper meumEntityMapper;

    @Override
    public void saveAndUpdateMeum(JSONObject jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        MeumEntityWithBLOBs meum = objectMapper.convertValue(jsonObject, MeumEntityWithBLOBs.class);
        meumEntityMapper.insertSelective(meum);
    }

    @Override
    public MeumVo selectMeum(Long meumId, String meumName, Long parentId, String parentMeumName, Integer needChild) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
