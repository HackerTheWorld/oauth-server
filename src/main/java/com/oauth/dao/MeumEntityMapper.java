package com.oauth.dao;

import java.util.List;

import com.oauth.entity.MeumEntity;
import com.oauth.entity.MeumEntityWithBLOBs;

public interface MeumEntityMapper {
    int deleteByPrimaryKey(Long meumId);

    int insert(MeumEntityWithBLOBs record);

    int insertSelective(MeumEntityWithBLOBs record);

    MeumEntityWithBLOBs selectByPrimaryKey(Long meumId);

    int updateByPrimaryKeySelective(MeumEntityWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MeumEntityWithBLOBs record);

    int updateByPrimaryKey(MeumEntity record);

    List<MeumEntityWithBLOBs> selectMeumByRoleId(Long roleId);
}