package com.oauth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import nonapi.io.github.classgraph.json.Id;
@ApiModel("用户与用户关系")
public class ParentUserEntity {
    @ApiModelProperty("用户与用户关系Id")
    @Id
    private Long parentUserId;
    @ApiModelProperty("用户Id")
    private Long userId;
    @ApiModelProperty("上级用户Id")
    private Long userParentId;

    public Long getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Long parentUserId) {
        this.parentUserId = parentUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserParentId() {
        return userParentId;
    }

    public void setUserParentId(Long userParentId) {
        this.userParentId = userParentId;
    }
}