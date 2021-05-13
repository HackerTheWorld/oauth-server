package com.oauth.entity;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("用户与角色关系")
public class UserRoleEntity {
    @ApiModelProperty("用户与角色关系Id")
    @Id
    private Long userRoleId;
    @ApiModelProperty("用户Id")
    private Long userId;
    @ApiModelProperty("角色Id")
    private Long roleId;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}