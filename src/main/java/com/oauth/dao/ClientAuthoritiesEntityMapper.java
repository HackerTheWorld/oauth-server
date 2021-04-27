package com.oauth.dao;

import com.oauth.entity.ClientAuthoritiesEntity;

public interface ClientAuthoritiesEntityMapper {
    int deleteByPrimaryKey(Long clientAuthoritiesId);

    int insert(ClientAuthoritiesEntity record);

    int insertSelective(ClientAuthoritiesEntity record);

    ClientAuthoritiesEntity selectByPrimaryKey(Long clientAuthoritiesId);

    int updateByPrimaryKeySelective(ClientAuthoritiesEntity record);

    int updateByPrimaryKey(ClientAuthoritiesEntity record);
}