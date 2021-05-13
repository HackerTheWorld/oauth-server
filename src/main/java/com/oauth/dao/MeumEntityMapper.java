package com.oauth.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.oauth.entity.MeumEntity;
import com.oauth.entity.MeumEntityWithBLOBs;
import com.oauth.tar.TreeTarget;
import com.oauth.vo.MeumVo;

@TreeTarget(treeTarget = "MeumTree")
public interface MeumEntityMapper extends TreeEntityMapper<MeumVo>{
    int deleteByPrimaryKey(Long meumId);

    int insert(MeumEntityWithBLOBs record);

    int insertSelective(MeumEntityWithBLOBs record);

    MeumEntityWithBLOBs selectByPrimaryKey(Long meumId);

    int updateByPrimaryKeySelective(MeumEntityWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MeumEntityWithBLOBs record);

    int updateByPrimaryKey(MeumEntity record);

    List<MeumEntityWithBLOBs> selectMeumByRoleId(Long roleId);

    List<MeumVo> selectMeumVos(HashMap<String,Object> param,Set<Long> meumIds);

    List<Long> selectIdByRole(Long roleId,String roleName);
}