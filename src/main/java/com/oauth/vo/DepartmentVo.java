package com.oauth.vo;

import java.util.Set;

import com.oauth.entity.DepartmentEntity;
import com.oauth.entity.DepartmentRelationshipEntity;
import com.oauth.entity.UserInforEntity;

public class DepartmentVo extends DepartmentEntity{

    private Set<DepartmentRelationshipEntity> parent;

    private Set<UserInforEntity> users;

    private Set<DepartmentVo> children;

    public Set<DepartmentRelationshipEntity> getParent() {
        return parent;
    }

    public void setParents(Set<DepartmentRelationshipEntity> parent) {
        this.parent = parent;
    }

    public Set<UserInforEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserInforEntity> users) {
        this.users = users;
    }

    public Set<DepartmentVo> getChildren() {
        return children;
    }

    public void setChildren(Set<DepartmentVo> children) {
        this.children = children;
    }

}
