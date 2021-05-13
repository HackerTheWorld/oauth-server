package com.oauth.dao;

import java.util.List;

import com.oauth.entity.RoleEntity;
import com.oauth.vo.RoleVo;

public interface RoleEntityMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(RoleEntity record);

    int insertSelective(RoleEntity record);

    RoleEntity selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(RoleEntity record);

    int updateByPrimaryKey(RoleEntity record);

    List<RoleVo> selectByIdName(Long roleId,String roleName);
}