package com.oauth.comon;

import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oauth.dao.RelationshipMapper;

import org.json.JSONArray;
import org.json.JSONObject;

public class RelationshipUtil {
    
    public static void relationship(JSONArray jsonArray,String colum,Long relId, Class cls, RelationshipMapper relationshipMapper) {
        if (jsonArray != null && !jsonArray.isEmpty()) {
          for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            jsonObject.put(colum, relId);
            ObjectMapper objectMapper = new ObjectMapper();
            Object record = objectMapper.convertValue(jsonObject, cls);
            Object objId = ReflexUtil.getValueByTarget(record,cls,org.springframework.data.annotation.Id.class);
            Long id = null;
            if(objId instanceof Long){
              id = (Long)objId;
            }
            if (id != null && id != 0) {
              relationshipMapper.updateByPrimaryKeySelective(record);
            } else {
              relationshipMapper.insertSelective(record);
            }
          }
        }
      }

}
