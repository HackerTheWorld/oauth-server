package com.oauth.service.impl;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.dao.PostEntityMapper;
import com.oauth.entity.PostEntity;
import com.oauth.service.PostService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostEntityMapper postEntityMapper;

    @Override
    public void saveAndUpdatePost(JSONObject jsonObject) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        PostEntity postEntity = objectMapper.convertValue(jsonObject, PostEntity.class);
        if(postEntity.getPostId() == null || postEntity.getPostId() == 0){
            postEntityMapper.insertSelective(postEntity);
        }else{
            postEntityMapper.updateByPrimaryKeySelective(postEntity);
        }
    }

    @Override
    public List<PostEntity> selectPost(Integer status, String postName, String postCode, Long postId) {
        return postEntityMapper.selectPost(status, postName, postCode, postId);
    }
    
}
