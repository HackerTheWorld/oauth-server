package com.oauth.entity;

public class MeumEntity {
    private Long meumId;

    private String meumName;

    private Long parentId;

    private String meumType;

    private Integer isRefresh;

    private Integer orderNumber;

    private Integer visible;

    private String target;
    
    private String parentPath;

    public Long getMeumId() {
        return meumId;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public void setMeumId(Long meumId) {
        this.meumId = meumId;
    }

    public String getMeumName() {
        return meumName;
    }

    public void setMeumName(String meumName) {
        this.meumName = meumName == null ? null : meumName.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMeumType() {
        return meumType;
    }

    public void setMeumType(String meumType) {
        this.meumType = meumType == null ? null : meumType.trim();
    }

    public Integer getIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(Integer isRefresh) {
        this.isRefresh = isRefresh;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }
}