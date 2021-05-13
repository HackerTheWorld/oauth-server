package com.oauth.entity;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("岗位新增修改信息")
public class PostEntity {
    @ApiModelProperty("岗位Id")
    private Long postId;
    @ApiModelProperty("岗位代码")
    private String postCode;
    @ApiModelProperty("岗位名称")
    private String postName;
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}