package com.oauth.receiving;

import java.util.List;

import com.oauth.entity.DepartmentEntity;
import com.oauth.entity.DepartmentLeaderEntity;
import com.oauth.entity.DepartmentRelationshipEntity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("部门新增修改信息")
public class DepartmentRece extends DepartmentEntity{

    @ApiModelProperty("部门领导")
    private List<DepartmentLeaderEntity> leader;
    @ApiModelProperty("上级部门")
    private List<DepartmentRelationshipEntity> parent;
    
    public List<DepartmentLeaderEntity> getLeader() {
        return leader;
    }
    public void setLeader(List<DepartmentLeaderEntity> leader) {
        this.leader = leader;
    }
    public List<DepartmentRelationshipEntity> getParent() {
        return parent;
    }
    public void setParent(List<DepartmentRelationshipEntity> parent) {
        this.parent = parent;
    }
    
    

}

