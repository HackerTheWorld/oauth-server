package com.oauth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.oauth.dao.DepartmentEntityMapper;
import com.oauth.dao.DepartmentLeaderEntityMapper;
import com.oauth.dao.DepartmentRelationshipEntityMapper;
import com.oauth.dao.UserInforEntityMapper;
import com.oauth.entity.DepartmentEntity;
import com.oauth.entity.DepartmentLeaderEntity;
import com.oauth.entity.DepartmentRelationshipEntity;
import com.oauth.entity.UserInforEntity;
import com.oauth.service.DepartmentService;
import com.oauth.vo.ChildCountVo;
import com.oauth.vo.DepartmentVo;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentEntityMapper departmentEntityMapper;
    @Autowired
    private DepartmentLeaderEntityMapper departmentLeaderEntityMapper;
    @Autowired
    private DepartmentRelationshipEntityMapper departmentRelationshipEntityMapper;
    @Autowired
    private UserInforEntityMapper userInforEntityMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAndUpdateDepartment(JSONObject jsonObject) {
        long departmentId = jsonObject.optLong("departmentId", -1);
        int sort = jsonObject.optInt("sort", -1);
        String departmentName = jsonObject.optString("departmentName", "");
        int status = jsonObject.optInt("status");

        DepartmentEntity department = new DepartmentEntity();
        department.setDepartmentName(departmentName);
        department.setSort(sort);
        department.setStatus(status);
        if (departmentId == -1) {
            departmentEntityMapper.insertSelective(department);
        } else {
            department.setDepartmentId(departmentId);
            departmentEntityMapper.updateByPrimaryKeySelective(department);
        }
        
        saveLeader(jsonObject.optJSONArray("leader"),department.getDepartmentId());
        saveParent(jsonObject.optJSONArray("parent"),department.getDepartmentId());
    }

    private void saveLeader(JSONArray leaderArray,long departmentId){
        if (leaderArray != null && !leaderArray.isEmpty()) {
            for (int i = 0; i < leaderArray.length(); i++) {
                JSONObject leaderObject = leaderArray.getJSONObject(i);
                long userId = leaderObject.getLong("userId");
                long departmentLeaderId = leaderObject.optLong("departmentLeaderId", -1);
                DepartmentLeaderEntity departmentLeaderEntity = new DepartmentLeaderEntity();
                departmentLeaderEntity.setDepartmentId(departmentId);
                departmentLeaderEntity.setUserId(userId);
                if (departmentLeaderId == -1) {
                    departmentLeaderEntityMapper.insertSelective(departmentLeaderEntity);
                } else {
                    departmentLeaderEntity.setDepartmentLeaderId(departmentLeaderId);
                    departmentLeaderEntityMapper.updateByPrimaryKeySelective(departmentLeaderEntity);
                }
            }

        }
    }

    private void saveParent(JSONArray parentArray,long departmentId){
        if (parentArray != null && !parentArray.isEmpty()) {
            for (int i = 0; i < parentArray.length(); i++) {
                JSONObject parentObject = parentArray.getJSONObject(i);
                long parentId = parentObject.optLong("parentId");
                String parentPath = parentObject.optString("parentPath","");
                long departmentRelationshipId = parentObject.optLong("departmentRelationshipId",-1);
                DepartmentRelationshipEntity departmentRelationshipEntity = new DepartmentRelationshipEntity();
                departmentRelationshipEntity.setDepartmentId(departmentId);
                departmentRelationshipEntity.setDepartmentParentId(parentId);
                departmentRelationshipEntity.setDepartmentPath(parentPath+parentId+"|");
                if (departmentRelationshipId == -1) {
                    departmentRelationshipEntityMapper.insertSelective(departmentRelationshipEntity);
                } else {
                    departmentRelationshipEntity.setDepartmentRelationshipId(departmentRelationshipId);
                    departmentRelationshipEntityMapper.updateByPrimaryKeySelective(departmentRelationshipEntity);
                }
            }
        }
    }

    @Override
    public List<DepartmentVo> selectDepartment(Integer status, String departmentName, Long departmentId, Long parentId,
            String parentName, Long userId, String username,Integer needChild) {

        HashMap<String,Object> param = new HashMap<>();
        List<Long> departmentList = new ArrayList<Long>();
        boolean needSearch = false; 
        if(userId != null || StringUtils.isNotBlank(username)){
            needSearch = true;
            departmentList.addAll(userInforEntityMapper.selectDepartmentByUsername(username,userId));
        }
        if(parentId != null || StringUtils.isNotBlank(parentName)){
            needSearch = true;
            List<Long> parentList = departmentEntityMapper.selectParentByName(parentName, parentId).stream().map(mapper -> mapper.getDepartmentId()).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(departmentList)){
                departmentList.addAll(parentList);
            }else{
                departmentList.retainAll(parentList);
            }
        }
        if(departmentId != null){
            needSearch = true;
            List<Long> depList = new ArrayList<Long>();
            depList.add(departmentId);
            if(CollectionUtils.isEmpty(departmentList)){
                departmentList.addAll(depList);
            }else{
                departmentList.retainAll(depList);
            }
        }
        if(needSearch && CollectionUtils.isEmpty(departmentList)){
            return null;
        }

        param.put("status", status);
        param.put("departmentName", departmentName);
        
        List<DepartmentVo> departmentVos = departmentEntityMapper.selectDepartment(param,departmentList);
        selectDepartment(departmentVos);
        if(needChild == 1){
            return departmentVos;
        }else {
            ChildCountVo countVo = new ChildCountVo();
            countVo.setNeedChild(needChild);
            allChild(departmentVos,countVo);
        }
        return departmentVos;
    }

    private void selectDepartment(List<DepartmentVo> departmentVos){

        for(DepartmentVo departmentVo:departmentVos){
            List<DepartmentRelationshipEntity> parents = departmentVo.getParents().stream().filter(predicate -> predicate.getDepartmentRelationshipId() != null).collect(Collectors.toList());
            List<UserInforEntity> users = departmentVo.getUsers().stream().filter(predicate -> predicate.getUserId() != null).collect(Collectors.toList());
            departmentVo.setParents(parents);
            departmentVo.setUsers(users);
        }
    }

    private void allChild(List<DepartmentVo> departmentVos,ChildCountVo countVo){
        for(DepartmentVo departmentVo : departmentVos){
            List<Long> parentList = departmentEntityMapper.selectParentByName(null, departmentVo.getDepartmentId()).stream().map(mapper -> mapper.getDepartmentId()).collect(Collectors.toList());
            countVo.setNeedChild(countVo.getNeedChild() - 1);
            if(countVo.getNeedChild() == 0 || CollectionUtils.isEmpty(parentList)){
                return;
            }
            List<DepartmentVo> parentDepartmentVos = departmentEntityMapper.selectDepartment(new HashMap<String,Object>(),parentList);
            if(!CollectionUtils.isEmpty(parentDepartmentVos)){
                selectDepartment(parentDepartmentVos);
                departmentVo.setChildren(parentDepartmentVos);
                allChild(parentDepartmentVos,countVo);
            }
            
        }
    }

}
