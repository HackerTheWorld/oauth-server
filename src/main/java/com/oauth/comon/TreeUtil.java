package com.oauth.comon;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.oauth.dao.TreeEntityMapper;

import org.springframework.util.CollectionUtils;

public class TreeUtil {

    public static void traversalTree(List list, int countVo,Class cls ,TreeEntityMapper treeEntityMapper, Consumer distinctVo,
            BiConsumer setChildren) {
        for (Object vo : list) {
            Object objId = ReflexUtil.getValueByTarget(vo,cls,org.springframework.data.annotation.Id.class);
            Long id = null;
            if(objId instanceof Long){
              id = (Long)objId;
            }
            List<Long> parentList = treeEntityMapper.selectIdChildByName(id);
            // todo 查询父节点id
            if (countVo - 1 == 0 || CollectionUtils.isEmpty(parentList)) {
                return;
            }
            // 查询对应节点的子节点数据
            List parentVos = treeEntityMapper.selectChild(parentList);
            if (!CollectionUtils.isEmpty(parentVos)) {
                distinctVo.accept(parentVos);
                setChildren.accept(parentVos, vo);
                traversalTree(parentVos, countVo - 1,cls ,treeEntityMapper, distinctVo, setChildren);
            }
        }
    }

}
