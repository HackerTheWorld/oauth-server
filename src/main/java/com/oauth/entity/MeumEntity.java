package com.oauth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("菜单新增修改信息")
public class MeumEntity {
    @ApiModelProperty("菜单id")
    private Long meumId;
    @ApiModelProperty("菜单名称")
    private String meumName;
    @ApiModelProperty("父菜单id")
    private Long parentId;
    @ApiModelProperty("菜单类型")
    private String meumType;
    @ApiModelProperty("是否刷新")
    private Integer isRefresh;
    @ApiModelProperty("菜单排序")
    private Integer orderNumber;
    @ApiModelProperty("是否显示")
    private Integer visible;
    @ApiModelProperty("菜单打开类型")
    private String target;
    @ApiModelProperty("上级菜单路径")
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