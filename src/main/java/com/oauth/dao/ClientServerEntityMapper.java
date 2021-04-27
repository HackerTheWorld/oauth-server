package com.oauth.dao;

import com.oauth.entity.ClientServerEntity;

public interface ClientServerEntityMapper {
    int deleteByPrimaryKey(Long clientServerId);

    int insert(ClientServerEntity record);

    int insertSelective(ClientServerEntity record);

    ClientServerEntity selectByPrimaryKey(Long clientServerId);

    ClientServerEntity selectByClientId(String clientId);

    int updateByPrimaryKeySelective(ClientServerEntity record);

    int updateByPrimaryKey(ClientServerEntity record);
}