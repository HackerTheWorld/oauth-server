package com.oauth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.comon.RelationshipUtil;
import com.oauth.comon.TreeUtil;
import com.oauth.dao.DepartmentEntityMapper;
import com.oauth.dao.RelationshipMapper;
import com.oauth.dao.TreeEntityMapper;
import com.oauth.dao.UserInforEntityMapper;
import com.oauth.entity.DepartmentEntity;
import com.oauth.entity.DepartmentLeaderEntity;
import com.oauth.service.DepartmentService;
import com.oauth.tar.RelationshipTarget;
import com.oauth.tar.TreeTarget;
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

    private final Map<String, TreeEntityMapper> treeMap;

    @Autowired
    public DepartmentServiceImpl(List<RelationshipMapper> relaMap, List<TreeEntityMapper> treeMap) {
        this.relaMap = relaMap.stream()
                .collect(Collectors.toMap(sfcInter -> Objects
                        .requireNonNull(AnnotationUtils.findAnnotation(sfcInter.getClass(), RelationshipTarget.class))
                        .relationshipTargetName(), v -> v, (v1, v2) -> v1));
        this.treeMap = treeMap.stream()
                .collect(Collectors.toMap(sfcInter -> Objects
                        .requireNonNull(AnnotationUtils.findAnnotation(sfcInter.getClass(), TreeTarget.class))
                        .treeTarget(), v -> v, (v1, v2) -> v1));
    }

    @Autowired
    private UserInforEntityMapper userInforEntityMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAndUpdateDepartment(JSONObject jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        DepartmentEntity department = objectMapper.convertValue(jsonObject, DepartmentEntity.class);
        if (StringUtils.isNotBlank(department.getDepartmentName())) {
            if (department.getDepartmentId() == null || department.getDepartmentId() == 0) {
                departmentEntityMapper.insertSelective(department);
            } else {
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
        Consumer<List<DepartmentVo>> distinctVo = (list) -> {
            for (DepartmentVo departmentVoEle : list) {
                departmentVoEle.setParents(departmentVoEle.getParent().stream()
                        .filter(predicate -> predicate.getDepartmentRelationshipId() != null)
                        .collect(Collectors.toSet()));
                departmentVoEle.setUsers(departmentVoEle.getUsers().stream()
                        .filter(predicate -> predicate.getUserId() != null).collect(Collectors.toSet()));
            }
        };

        BiConsumer<Set<DepartmentVo>, DepartmentVo> setChildren = (list, departVo) -> {
            departVo.setChildren(list);
        };

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
        distinctVo.accept(departmentVos);

        if (needChild == 1) {
            return departmentVos;
        } else {
            TreeUtil.traversalTree(departmentVos, needChild, DepartmentVo.class, treeMap.get("DepartmentTree"),
                    distinctVo, setChildren);
        }
        return departmentVos;
    }
}
