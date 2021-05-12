package com.oauth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.oauth.comon.RelationshipUtil;
import com.oauth.dao.DepartmentEntityMapper;
import com.oauth.dao.RelationshipMapper;
import com.oauth.dao.UserInforEntityMapper;
import com.oauth.entity.DepartmentEntity;
import com.oauth.entity.DepartmentLeaderEntity;
import com.oauth.entity.DepartmentRelationshipEntity;
import com.oauth.entity.UserInforEntity;
import com.oauth.service.DepartmentService;
import com.oauth.tar.RelationshipTar;
import com.oauth.vo.ChildCountVo;
import com.oauth.vo.DepartmentVo;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentEntityMapper departmentEntityMapper;

    private final Map<String, RelationshipMapper> relaMap;

    @Autowired
    public DepartmentServiceImpl(List<RelationshipMapper> sfcInterListAuto) {
        relaMap = sfcInterListAuto.stream()
                .collect(Collectors.toMap(sfcInter -> Objects
                        .requireNonNull(AnnotationUtils.findAnnotation(sfcInter.getClass(), RelationshipTar.class))
                        .relationshipTarName(), v -> v, (v1, v2) -> v1));
    }

    @Autowired
    private UserInforEntityMapper userInforEntityMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAndUpdateDepartment(JSONObject jsonObject) {
        long departmentId = jsonObject.optLong("departmentId", 0);
        Integer sort = jsonObject.optInt("sort", 0);
        String departmentName = jsonObject.optString("departmentName", "");
        int status = jsonObject.optInt("status", 0);

        DepartmentEntity department = new DepartmentEntity();
        department.setDepartmentName(departmentName);
        department.setSort(sort);
        department.setStatus(status);
        if (StringUtils.isNotBlank(departmentName)) {
            if (departmentId == 0) {
                departmentEntityMapper.insertSelective(department);
            } else {
                department.setDepartmentId(departmentId);
                departmentEntityMapper.updateByPrimaryKeySelective(department);
            }

            RelationshipUtil.relationship(jsonObject.optJSONArray("leader"), "departmentId",
                    department.getDepartmentId(), DepartmentLeaderEntity.class, relaMap.get("DepartmentLeader"));
            RelationshipUtil.relationship(jsonObject.optJSONArray("parent"), "departmentId",
                    department.getDepartmentId(), DepartmentLeaderEntity.class, relaMap.get("DepartmentRelationship"));
        }

    }

    @Override
    public List<DepartmentVo> selectDepartment(Integer status, String departmentName, Long departmentId, Long parentId,
            String parentName, Long userId, String username, Integer needChild) {

        HashMap<String, Object> param = new HashMap<>();
        List<Long> departmentList = new ArrayList<Long>();
        boolean needSearch = false;
        if (userId != null || StringUtils.isNotBlank(username)) {
            needSearch = true;
            departmentList.addAll(userInforEntityMapper.selectDepartmentByUsername(username, userId));
        }
        if (parentId != null || StringUtils.isNotBlank(parentName)) {
            List<Long> parentList = departmentEntityMapper.selectParentByName(parentName, parentId).stream()
                    .map(mapper -> mapper.getDepartmentId()).collect(Collectors.toList());
            if (needSearch) {
                departmentList.retainAll(parentList);
            } else {
                departmentList.addAll(parentList);
            }
            needSearch = true;
        }
        if (departmentId != null) {
            List<Long> depList = new ArrayList<Long>();
            depList.add(departmentId);
            if (needSearch) {
                departmentList.retainAll(depList);
            } else {
                departmentList.addAll(depList);
            }
        }
        if (needSearch && CollectionUtils.isEmpty(departmentList)) {
            return new ArrayList<>();
        }

        param.put("status", status);
        param.put("departmentName", departmentName);

        List<DepartmentVo> departmentVos = departmentEntityMapper.selectDepartment(param, departmentList);
        selectDepartment(departmentVos);
        if (needChild == 1) {
            return departmentVos;
        } else {
            ChildCountVo countVo = new ChildCountVo();
            countVo.setNeedChild(needChild);
            allChild(departmentVos, countVo);
        }
        return departmentVos;
    }

    private void selectDepartment(List<DepartmentVo> departmentVos) {

        for (DepartmentVo departmentVo : departmentVos) {
            List<DepartmentRelationshipEntity> parents = departmentVo.getParents().stream()
                    .filter(predicate -> predicate.getDepartmentRelationshipId() != null).collect(Collectors.toList());
            List<UserInforEntity> users = departmentVo.getUsers().stream()
                    .filter(predicate -> predicate.getUserId() != null).collect(Collectors.toList());
            departmentVo.setParents(parents);
            departmentVo.setUsers(users);
        }
    }

    private void allChild(List<DepartmentVo> departmentVos, ChildCountVo countVo) {
        for (DepartmentVo departmentVo : departmentVos) {
            List<Long> parentList = departmentEntityMapper.selectChildByName(departmentVo.getDepartmentId()).stream()
                    .map(mapper -> mapper.getDepartmentId()).collect(Collectors.toList());
            countVo.setNeedChild(countVo.getNeedChild() - 1);
            if (countVo.getNeedChild() == 0 || CollectionUtils.isEmpty(parentList)) {
                return;
            }
            List<DepartmentVo> parentDepartmentVos = departmentEntityMapper
                    .selectDepartment(new HashMap<String, Object>(), parentList);
            if (!CollectionUtils.isEmpty(parentDepartmentVos)) {
                selectDepartment(parentDepartmentVos);
                departmentVo.setChildren(parentDepartmentVos);
                allChild(parentDepartmentVos, countVo);
            }

        }
    }

}
