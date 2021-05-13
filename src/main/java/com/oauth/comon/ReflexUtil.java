package com.oauth.comon;

import java.lang.reflect.Field;

public class ReflexUtil {

    public static Object getValueByTarget(Object record, Class cls, Class target) {
        Object obj = null;
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(target)) {
                try {
                    field.setAccessible(true);
                    obj = field.getLong(record);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return obj;
    }

}
