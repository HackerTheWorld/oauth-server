package com.oauth.entity;

import nonapi.io.github.classgraph.json.Id;

public class DepartmentUserEntity {
    @Id
    private Long departmentUserId;

    private Long departmentId;

    private Long userId;

    public Long getDepartmentUserId() {
        return departmentUserId;
    }

    public void setDepartmentUserId(Long departmentUserId) {
        this.departmentUserId = departmentUserId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}