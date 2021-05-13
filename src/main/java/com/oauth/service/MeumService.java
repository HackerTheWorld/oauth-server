package com.oauth.service;

import java.util.List;

import com.oauth.entity.MeumEntityWithBLOBs;
import com.oauth.vo.MeumVo;

public interface MeumService {
    
    void saveAndUpdateMeum(MeumEntityWithBLOBs meumEntity);

    List<MeumVo> selectMeum(Long meumId,String meumName,Long parentId,Long roleId,Integer needChild);
}
