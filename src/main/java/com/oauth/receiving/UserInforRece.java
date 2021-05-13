package com.oauth.receiving;

import java.util.List;

import com.oauth.entity.DepartmentUserEntity;
import com.oauth.entity.ParentUserEntity;
import com.oauth.entity.PostUserEntity;
import com.oauth.entity.UserInforEntity;
import com.oauth.entity.UserRoleEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户新增修改信息")
public class UserInforRece extends UserInforEntity{
    @ApiModelProperty("用户和部门关系")
    private List<DepartmentUserEntity> department;

    @ApiModelProperty("用户与岗位关系")
    private List<PostUserEntity> post;
    
    @ApiModelProperty("用户与用户关系")
    private List<ParentUserEntity> parent;
    
    @ApiModelProperty("用户与角色关系")
    private List<UserRoleEntity> role;

    public List<DepartmentUserEntity> getDepartment() {
        return department;
    }
    public void setDepartment(List<DepartmentUserEntity> department) {
        this.department = department;
    }
    public List<PostUserEntity> getPost() {
        return post;
    }
    public void setPost(List<PostUserEntity> post) {
        this.post = post;
    }
    public List<ParentUserEntity> getParent() {
        return parent;
    }
    public void setParent(List<ParentUserEntity> parent) {
        this.parent = parent;
    }
    public List<UserRoleEntity> getRole() {
        return role;
    }
    public void setRole(List<UserRoleEntity> role) {
        this.role = role;
    }

    
}
