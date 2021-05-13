package com.oauth.entity;

import org.springframework.data.annotation.Id;

public class RoleMeumEntity {
    @Id
    private Long roleMeumId;

    private Long meumId;

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