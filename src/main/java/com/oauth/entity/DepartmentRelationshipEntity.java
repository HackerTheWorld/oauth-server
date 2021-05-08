package com.oauth.entity;

public class DepartmentRelationshipEntity {
    private Long departmentRelationshipId;

    private Long departmentId;

    private Long departmentParentId;

    private String departmentPath;

    public Long getDepartmentRelationshipId() {
        return departmentRelationshipId;
    }

    public void setDepartmentRelationshipId(Long departmentRelationshipId) {
        this.departmentRelationshipId = departmentRelationshipId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getDepartmentParentId() {
        return departmentParentId;
    }

    public void setDepartmentParentId(Long departmentParentId) {
        this.departmentParentId = departmentParentId;
    }

    public String getDepartmentPath() {
        return departmentPath;
    }

    public void setDepartmentPath(String departmentPath) {
        this.departmentPath = departmentPath == null ? null : departmentPath.trim();
    }
}