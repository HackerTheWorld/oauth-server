package com.oauth.service.impl;

import com.oauth.entity.RoleEntity;
import com.oauth.service.RoleService;
import com.oauth.tar.RelationshipTarget;
import com.oauth.vo.RoleVo;
import com.oauth.entity.RoleMeumEntity;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.comon.RelationshipUtil;
import com.oauth.dao.RelationshipMapper;
import com.oauth.dao.RoleEntityMapper;;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleEntityMapper roleEntityMapper;

    private final Map<String, RelationshipMapper> relaMap;

    @Autowired
    public RoleServiceImpl(List<RelationshipMapper> sfcInterListAuto) {
        relaMap = sfcInterListAuto.stream()
                .collect(Collectors.toMap(sfcInter -> Objects
                        .requireNonNull(AnnotationUtils.findAnnotation(sfcInter.getClass(), RelationshipTarget.class))
                        .relationshipTargetName(), v -> v, (v1, v2) -> v1));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAndUpdateRole(JSONObject jsonObject) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        RoleEntity roleEntity = objectMapper.convertValue(jsonObject, RoleEntity.class);
        if (roleEntity.getRoleId() == null || roleEntity.getRoleId() == 0) {
            roleEntityMapper.insertSelective(roleEntity);
        } else {
            roleEntityMapper.updateByPrimaryKeySelective(roleEntity);
        }
        RelationshipUtil.relationship(jsonObject.optJSONArray("role"), "roleId", roleEntity.getRoleId(),
                RoleMeumEntity.class, relaMap.get("RoleMeum"));
    }

    @Override
    public List<RoleVo> selectRole(Long roleId, String roleName) {
        List<RoleVo> roleVos = roleEntityMapper.selectByIdName(roleId, roleName);
        for (RoleVo role : roleVos) {
            role.setMeum(role.getMeum().stream().filter(predicate -> predicate.getMeumId() != null).distinct()
                    .collect(Collectors.toList()));
        }
        return roleVos;
    }

}