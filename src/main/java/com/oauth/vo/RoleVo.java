package com.oauth.vo;

import java.util.List;

import com.oauth.entity.MeumEntityWithBLOBs;
import com.oauth.entity.RoleEntity;

public class RoleVo extends RoleEntity{
    List<MeumEntityWithBLOBs> meum;

    public List<MeumEntityWithBLOBs> getMeum() {
        return meum;
    }

    public void setMeum(List<MeumEntityWithBLOBs> meum) {
        this.meum = meum;
    }

}
