package com.oauth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import nonapi.io.github.classgraph.json.Id;

@ApiModel("上级部门")
public class DepartmentRelationshipEntity {
    @ApiModelProperty("部门关系id")
    @Id
    private Long departmentRelationshipId;
    @ApiModelProperty("部门id")
    private Long departmentId;
    @ApiModelProperty("上级部门id")
    private Long departmentParentId;
    @ApiModelProperty("上级部门路径")
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