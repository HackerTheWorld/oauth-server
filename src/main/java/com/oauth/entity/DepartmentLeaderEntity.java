package com.oauth.entity;

public class DepartmentLeaderEntity {
    private Long departmentLeaderId;

    private Long departmentId;

    private Long userId;

    public Long getDepartmentLeaderId() {
        return departmentLeaderId;
    }

    public void setDepartmentLeaderId(Long departmentLeaderId) {
        this.departmentLeaderId = departmentLeaderId;
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