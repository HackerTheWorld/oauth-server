package com.oauth.entity;

public class UserClientEntity {
    private Long id;

    private Long userId;

    private Long clientAuthoritiesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClientAuthoritiesId() {
        return clientAuthoritiesId;
    }

    public void setClientAuthoritiesId(Long clientAuthoritiesId) {
        this.clientAuthoritiesId = clientAuthoritiesId;
    }
}