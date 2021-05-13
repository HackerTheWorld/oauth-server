package com.oauth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("菜单新增修改信息")
public class MeumEntityWithBLOBs extends MeumEntity {
    @ApiModelProperty("菜单连接地址")
    private String url;
    @ApiModelProperty("菜单图标")
    private String icon;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}