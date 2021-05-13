package com.oauth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.comon.TreeUtil;
import com.oauth.dao.MeumEntityMapper;
import com.oauth.dao.TreeEntityMapper;
import com.oauth.entity.MeumEntityWithBLOBs;
import com.oauth.service.MeumService;
import com.oauth.tar.TreeTarget;
import com.oauth.vo.MeumVo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;

import io.swagger.v3.oas.annotations.servers.Server;

@Server
public class MeumServiceImpl implements MeumService {

    @Autowired
    private MeumEntityMapper meumEntityMapper;

    private final Map<String, TreeEntityMapper> treeMap;

    @Autowired
    public MeumServiceImpl(List<TreeEntityMapper> treeMap) {
        this.treeMap = treeMap.stream()
                .collect(Collectors.toMap(sfcInter -> Objects
                        .requireNonNull(AnnotationUtils.findAnnotation(sfcInter.getClass(), TreeTarget.class))
                        .treeTarget(), v -> v, (v1, v2) -> v1));
    }

    @Override
    public void saveAndUpdateMeum(JSONObject jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        MeumEntityWithBLOBs meum = objectMapper.convertValue(jsonObject, MeumEntityWithBLOBs.class);
        meumEntityMapper.insertSelective(meum);
    }

    @Override
    public List<MeumVo> selectMeum(Long meumId, String meumName, Long parentId, Long roleId, Integer needChild) {
        Consumer<List<MeumVo>> distinctVo = (list) -> {
            for (MeumVo meumVo : list) {
                meumVo.setRoles(meumVo.getRoles().stream().filter(predicate -> predicate.getRoleId() != null)
                        .collect(Collectors.toSet()));
            }
        };
        BiConsumer<Set<MeumVo>, MeumVo> setChildren = (list, vo) -> {
            vo.setChildren(list);
        };
        Set<Long> meumIds = new HashSet<>();
        boolean needSearch = false;
        if (roleId != null && roleId != 0) {
            List<MeumEntityWithBLOBs> meumLists = meumEntityMapper.selectMeumByRoleId(roleId);
            needSearch = true;
            if (CollectionUtils.isEmpty(meumLists)) {
                return new ArrayList();
            } else {
                meumIds = meumEntityMapper.selectMeumByRoleId(roleId).stream().map(mapper -> mapper.getMeumId())
                        .collect(Collectors.toSet());
            }
        }
        if (meumId != null && meumId != 0) {
            Set<Long> meumSet = new HashSet<Long>();
            meumSet.add(meumId);
            if (needSearch) {
                meumIds.retainAll(meumSet);
            } else {
                meumIds.addAll(meumSet);
            }
        }
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("meumName", meumName);
        param.put("parentId", parentId);

        List<MeumVo> meumVos = meumEntityMapper.selectMeumVos(param, meumIds);
        distinctVo.accept(meumVos);

        if (needChild == 1) {
            return meumVos;
        } else {
            TreeUtil.traversalTree(meumVos, needChild, MeumVo.class, treeMap.get("MeumTree"), distinctVo, setChildren);
        }

        return meumVos;
    }

}
