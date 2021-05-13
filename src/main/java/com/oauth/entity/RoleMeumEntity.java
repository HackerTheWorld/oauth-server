package com.oauth.entity;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("角色菜单关系")
public class RoleMeumEntity {
    @ApiModelProperty("角色菜单关系Id")
    @Id
    private Long roleMeumId;
    @ApiModelProperty("菜单Id")
    private Long meumId;
    @ApiModelProperty("角色Id")
    private Long roleId;

    public Long getRoleMeumId() {
        return roleMeumId;
    }

    public void setRoleMeumId(Long roleMeumId) {
        this.roleMeumId = roleMeumId;
    }

    public Long getMeumId() {
        return meumId;
    }

    public void setMeumId(Long meumId) {
        this.meumId = meumId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}