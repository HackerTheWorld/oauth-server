package com.oauth.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户信息")
public class UserInforEntity {
    @ApiModelProperty("用户Id")
    private Long userId;
    @ApiModelProperty("账户名称")
    private String username;
    @ApiModelProperty("账户密码")
    private String password;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("真实性名")
    private String realname;
    @ApiModelProperty("邮箱")
    private String email;
    @JsonIgnore
    private List<ClientAuthoritiesEntity> roles;

    public List<ClientAuthoritiesEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<ClientAuthoritiesEntity> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}