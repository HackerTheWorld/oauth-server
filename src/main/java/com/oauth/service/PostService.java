package com.oauth.service;

import java.util.List;

import com.oauth.entity.PostEntity;

import org.json.JSONObject;

public interface PostService {

    void saveAndUpdatePost(JSONObject jsonObject) throws Exception;

    List<PostEntity> selectPost(Integer status, String postName, String postCode, Long postId);
}
