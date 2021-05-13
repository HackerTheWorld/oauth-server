package com.oauth.dao;

public interface RelationshipMapper<T> {
    
    int insertSelective(T record);

    int updateByPrimaryKeySelective(T record);
}
