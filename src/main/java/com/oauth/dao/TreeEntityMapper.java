package com.oauth.dao;

import java.util.List;

public interface TreeEntityMapper<T> {

   List<Long> selectIdChildByName(Long parentId);

   List<T> selectChild(List<Long> parentList);

}
