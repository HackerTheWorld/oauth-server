package com.oauth.vo;

import java.util.Set;

import com.oauth.entity.RoleEntity;
import com.oauth.entity.MeumEntity;

public class MeumVo extends MeumEntity{

    private Set<RoleEntity> roles;

    private Set<MeumEntity> parent;

    private Set<MeumVo> children;

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<MeumEntity> getParent() {
        return parent;
    }

    public void setParent(Set<MeumEntity> parent) {
        this.parent = parent;
    }

    public Set<MeumVo> getChildren() {
        return children;
    }

    public void setChildren(Set<MeumVo> children) {
        this.children = children;
    }

    
    
}
