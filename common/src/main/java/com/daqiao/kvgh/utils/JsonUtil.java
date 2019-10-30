package com.daqiao.kvgh.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : saizhang
 * @Date : 2019/09/08
 * @Time : 9:10
 * @Description : TODO
 */
public class JsonUtil {

    public static <T> List<String> getColumnNameList(Class<T> clazz) {
        List<String> list = new ArrayList<>();
        Map<Integer, String> map = new HashMap<>(16);
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
            int index = jsonProperty.index();
            String value = jsonProperty.value();
            map.put(index, value);
        }
        for (int i = 0; i < fields.length; i++) {
            String value = map.get(i);
            if (value == null) {
                throw new RuntimeException();
            }
            list.add(value);
        }
        return list;
    }

    public static <T> List<Map<String, String>> getColumnValueList(List<T> list, Class<T> clazz) {
        List<Map<String, String>> ret = new ArrayList<>();
        for (T obj : list) {
            Map<String, String> map = new HashMap<>(16);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
                field.setAccessible(true);
                String key = jsonProperty.value();
                String value = "";
                try {
                    value = field.get(obj).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                map.put(key, value);
            }
            ret.add(map);
        }
        return ret;
    }

    public static <T> Object getObject(Map<String, String> map, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        Object object = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
            field.setAccessible(true);
            String key = jsonProperty.value();
            if (map.get(key) != null) {
                if (field.getType() == String.class) {
                    field.set(object, map.get(key));
                } else if (field.getType() == Boolean.class) {
                    if ("TRUE".equals(map.get(key)) || "true".equals(map.get(key))) {
                        field.set(object, true);
                    } else {
                        field.set(object, false);
                    }
                } else if( field.getType() == Double.class ) {
                    field.set(object, Double.parseDouble(map.get(key)));
                } else if( field.getType() == Integer.class ) {
                    try {
                        field.set(object, new Double(Double.parseDouble(map.get(key))).intValue());
                    } catch(Exception e) {
                        field.set(object, 0);
                    }
                }
            }
        }
        return object;
    }

}
