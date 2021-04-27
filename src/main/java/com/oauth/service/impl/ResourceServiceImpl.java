package com.oauth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import com.oauth.contons.MessageConstant;
import com.oauth.service.ResourceService;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

  private final RedisTemplate<String, Object> redisTemplate;

  public ResourceServiceImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @PostConstruct
  public void initData() {
    Map<String, List<String>> resourceRolesMap = new TreeMap<>();
    ArrayList<String> aList = new ArrayList<String>();
    aList.add("ADMIN");
    resourceRolesMap.put("/productivity-component-summary/swagger-ui.html", aList);
    ArrayList<String> aListStr = new ArrayList<String>();
    aListStr.add("ADMIN");
    aListStr.add("USER");
    resourceRolesMap.put("/productivity-component-summary/swagger-ui.html", aListStr);
    redisTemplate.opsForHash().putAll(MessageConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
  }
}