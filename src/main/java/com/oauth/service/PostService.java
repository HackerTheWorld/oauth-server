package com.oauth.service;

import java.util.List;

import com.oauth.entity.PostEntity;

public interface PostService {

    void saveAndUpdatePost(PostEntity postEntity) throws Exception;

    List<PostEntity> selectPost(Integer status, String postName, String postCode, Long postId);
}
