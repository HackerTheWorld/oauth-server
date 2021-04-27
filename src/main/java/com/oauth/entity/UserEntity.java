package com.oauth.entity;

import java.util.List;

public class UserEntity {
    private Long id;

    private String username;

    private String password;

    private Integer status;

    private List<ClientAuthoritiesEntity> roles;

    public List<ClientAuthoritiesEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<ClientAuthoritiesEntity> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}