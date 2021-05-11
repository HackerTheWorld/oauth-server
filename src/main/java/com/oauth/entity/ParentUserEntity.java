package com.oauth.entity;

public class ParentUserEntity {
    private Long parentUserId;

    private Long userId;

    private Long userParentId;

    public Long getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Long parentUserId) {
        this.parentUserId = parentUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserParentId() {
        return userParentId;
    }

    public void setUserParentId(Long userParentId) {
        this.userParentId = userParentId;
    }
}