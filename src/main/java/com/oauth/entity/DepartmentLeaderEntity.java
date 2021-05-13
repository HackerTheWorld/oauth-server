package com.oauth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import nonapi.io.github.classgraph.json.Id;

@ApiModel("部门领导")
public class DepartmentLeaderEntity {
    @ApiModelProperty("部门领导关系Id")
    @Id
    private Long departmentLeaderId;
    @ApiModelProperty("部门Id")
    private Long departmentId;
    @ApiModelProperty("用户Id")
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