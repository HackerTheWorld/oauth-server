package com.oauth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import nonapi.io.github.classgraph.json.Id;
@ApiModel("用户与岗位关系")
public class PostUserEntity {
    @ApiModelProperty("用户与岗位关系Id")
    @Id
    private Long postUserId;
    @ApiModelProperty("用户Id")
    private Long userId;
    @ApiModelProperty("岗位Id")
    private Long postId;

    public Long getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(Long postUserId) {
        this.postUserId = postUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}