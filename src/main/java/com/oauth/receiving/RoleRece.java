package com.oauth.receiving;

import java.util.List;

import com.oauth.entity.RoleEntity;
import com.oauth.entity.RoleMeumEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("角色新增修改信息")
public class RoleRece extends RoleEntity{
    @ApiModelProperty("菜单")
    private List<RoleMeumEntity> meum;

    public List<RoleMeumEntity> getMeum() {
        return meum;
    }

    public void setMeum(List<RoleMeumEntity> meum) {
        this.meum = meum;
    }

}
