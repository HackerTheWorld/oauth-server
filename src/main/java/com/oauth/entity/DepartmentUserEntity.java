package com.oauth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import nonapi.io.github.classgraph.json.Id;
@ApiModel("用户和部门关系")
public class DepartmentUserEntity {
    @ApiModelProperty("用户和部门关系Id")
    @Id
    private Long departmentUserId;
    @ApiModelProperty("部门Id")
    private Long departmentId;
    @ApiModelProperty("用户Id")
    private Long userId;

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
}