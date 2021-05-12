package com.oauth.dao;

public interface RelationshipMapper<T> {
    
    int insertSelective(T record);

    int updateByPrimaryKey(T record);
}
