package com.oauth.vo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.oauth.entity.DepartmentRelationshipEntity;
import com.oauth.entity.UserInforEntity;

public class DepartmentVo {
    private Long departmentId;

    private String departmentName;

    private Integer sort;

    private LocalDateTime createdTime;

    private Integer status;

    private List<DepartmentRelationshipEntity> parents;

    private List<UserInforEntity> users;

    private List<DepartmentVo> children;

    public List<DepartmentRelationshipEntity> getParents() {
        return parents;
    }

    public void setParents(List<DepartmentRelationshipEntity> parents) {
        this.parents = parents.stream().filter(predicate -> predicate.getDepartmentRelationshipId()!=null).collect(Collectors.toList());
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<UserInforEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserInforEntity> users) {
        this.users = users.stream().filter(predicate -> predicate.getUserId()!=null).collect(Collectors.toList());
    }

    public List<DepartmentVo> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentVo> children) {
        this.children = children;
    }

}
