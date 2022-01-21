package com.llys.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.llys.data.Student;

public final class JsonUtils {
    public static void main(String[] args) {
        String s = "[[\"2021.09.08\",\"08:30-18:00\",\"啥也不干\"],[\"2021.09.09\",\"08:30-18:00\",\"干呆着\"]]";
        List<List> list = JSON.parseArray(s, List.class);
        System.out.println(list);
        System.out.println(convertObjToStr(list));
    }

    public static String convertObjToStr(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.PrettyFormat, SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteMapNullValue, SerializerFeature.SortField);
    }

    public static Student convertStrToObj(String str) {
        return JSON.parseObject(str, Student.class);
    }

}
