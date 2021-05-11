package com.oauth.dao;

import java.util.HashMap;
import java.util.List;

import com.oauth.entity.UserInforEntity;
import com.oauth.vo.UserInforVo;

public interface UserInforEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInforEntity record);

    int insertSelective(UserInforEntity record);

    UserInforEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInforEntity record);

    int updateByPrimaryKey(UserInforEntity record);

    List<UserInforEntity> selectByUsername(String username,Long userId);

    List<Long> selectDepartmentByUsername(String username,Long userId);

    List<Long> selectPostUserByName(Long postId, String post,String postCode);

    List<Long> selectDepartmentUserByName(Long departmentId, String departmentName);

    List<Long> selectParentUserByName(Long parentId, String parentName);

    List<UserInforVo> selectUserInforVos(HashMap<String,Object> param,List<Long> userIds);
}