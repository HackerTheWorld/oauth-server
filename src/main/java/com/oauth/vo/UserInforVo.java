package com.oauth.vo;

import java.util.List;

import com.oauth.entity.DepartmentEntity;
import com.oauth.entity.PostEntity;

public class UserInforVo {
    private Long userId;

    private String username;

    private Integer status;

    private String phone;

    private String realname;

    private String email;

    private List<UserInforVo> parent;

    private List<UserInforVo> child;

    private List<DepartmentEntity> departmentUser;

    private List<PostEntity> postUserEntity;

    public List<UserInforVo> getParent() {
        return parent;
    }

    public void setParent(List<UserInforVo> parent) {
        this.parent = parent;
    }

    public List<UserInforVo> getChild() {
        return child;
    }

    public void setChild(List<UserInforVo> child) {
        this.child = child;
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
        this.username = username;
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
        this.phone = phone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DepartmentEntity> getDepartmentUser() {
        return departmentUser;
    }

    public void setDepartmentUser(List<DepartmentEntity> departmentUser) {
        this.departmentUser = departmentUser;
    }

    public List<PostEntity> getPostUserEntity() {
        return postUserEntity;
    }

    public void setPostUserEntity(List<PostEntity> postUserEntity) {
        this.postUserEntity = postUserEntity;
    }

}
