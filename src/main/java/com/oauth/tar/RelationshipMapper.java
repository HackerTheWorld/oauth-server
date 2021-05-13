package com.oauth.tar;

public interface RelationshipMapper<T> {
    
    int insertSelective(T record);

    int updateByPrimaryKeySelective(T record);
}
