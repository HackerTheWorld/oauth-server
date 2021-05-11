package com.oauth.service.impl;

import java.util.List;

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
        Long postId = jsonObject.optLong("postId", 0);
        String postCode = jsonObject.optString("postCode","");
        String postName = jsonObject.optString("postName","");
        Integer sort = jsonObject.optInt("sort",0);
        Integer status = jsonObject.optInt("status",0);

        PostEntity postEntity = new PostEntity();
        postEntity.setPostCode(postCode);
        postEntity.setPostName(postName);
        postEntity.setSort(sort);
        postEntity.setStatus(status);
        if(postId == 0){
            postEntityMapper.insertSelective(postEntity);
        }else{
            postEntity.setPostId(postId);
            postEntityMapper.updateByPrimaryKeySelective(postEntity);
        }
    }

    @Override
    public List<PostEntity> selectPost(Integer status, String postName, String postCode, Long postId) {
        return postEntityMapper.selectPost(status, postName, postCode, postId);
    }
    
}
