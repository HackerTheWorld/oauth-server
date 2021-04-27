package com.oauth.vo;

public class Client {
    private String clientId;
    private String resourceIds;
    private Boolean secretRequire;
    private String clientSecret;
    private Boolean scopeRequire;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;

    public Client() {

    }

    public Client(String clientId, String resourceIds, Boolean secretRequire, String clientSecret, Boolean scopeRequire,
            String scope, String authorizedGrantTypes, String webServerRedirectUri, String authorities,
            Integer accessTokenValidity, Integer refreshTokenValidity) {
        this.clientId = clientId;
        this.resourceIds = resourceIds;
        this.secretRequire = secretRequire;
        this.clientSecret = clientSecret;
        this.scopeRequire = scopeRequire;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.webServerRedirectUri = webServerRedirectUri;
        this.authorities = authorities;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public static Client builder() {
        return new Client();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Client clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Client resourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
        return this;
    }

    public Boolean getSecretRequire() {
        return secretRequire;
    }

    public void setSecretRequire(Boolean secretRequire) {
        this.secretRequire = secretRequire;
    }

    public Client secretRequire(Boolean secretRequire) {
        this.secretRequire = secretRequire;
        return this;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Client clientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public Boolean getScopeRequire() {
        return scopeRequire;
    }

    public void setScopeRequire(Boolean scopeRequire) {
        this.scopeRequire = scopeRequire;
    }

    public Client scopeRequire(Boolean scopeRequire) {
        this.scopeRequire = scopeRequire;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Client scope(String scope) {
        this.scope = scope;
        return this;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public Client authorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
        return this;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public Client webServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
        return this;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Client authorities(String authorities) {
        this.authorities = authorities;
        return this;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Client accessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
        return this;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public Client refreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
        return this;
    }

}