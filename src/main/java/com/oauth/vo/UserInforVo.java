package com.oauth.vo;

import java.util.Set;

import com.oauth.entity.DepartmentEntity;
import com.oauth.entity.MeumEntityWithBLOBs;
import com.oauth.entity.PostEntity;
import com.oauth.entity.RoleEntity;
import com.oauth.entity.UserInforEntity;

public class UserInforVo extends UserInforEntity{

    private Set<UserInforVo> parent;

    private Set<UserInforVo> children;

    private Set<RoleEntity> roleEntity;

    private Set<MeumEntityWithBLOBs> meumEntity;

    private Set<DepartmentEntity> departmentUser;

    private Set<PostEntity> postEntity;

    public Set<MeumEntityWithBLOBs> getMeumEntity() {
        return meumEntity;
    }

    public void setMeumEntity(Set<MeumEntityWithBLOBs> meumEntity) {
        this.meumEntity = meumEntity;
    }

    public Set<RoleEntity> getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(Set<RoleEntity> roleEntity) {
        this.roleEntity = roleEntity;
    }

    public Set<UserInforVo> getParent() {
        return parent;
    }

    public void setParent(Set<UserInforVo> parent) {
        this.parent = parent;
    }

    public Set<UserInforVo> getChildren() {
        return children;
    }

    public void setChildren(Set<UserInforVo> children) {
        this.children = children;
    }

    public Set<DepartmentEntity> getDepartmentUser() {
        return departmentUser;
    }

    public void setDepartmentUser(Set<DepartmentEntity> departmentUser) {
        this.departmentUser = departmentUser;
    }

    public Set<PostEntity> getPostEntity() {
        return postEntity;
    }

    public void setPostEntity(Set<PostEntity> postEntity) {
        this.postEntity = postEntity;
    }

}
