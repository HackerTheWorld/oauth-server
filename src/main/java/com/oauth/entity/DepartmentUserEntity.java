package com.oauth.entity;

public class DepartmentUserEntity {
    private Long departmentUserId;

    private Long departmentId;

    private Long userId;

    private Long postId;

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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}