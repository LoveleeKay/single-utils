package utils;

import com.alibaba.fastjson.JSON;
import data.Student;

import java.util.ArrayList;
import java.util.List;

public final class JsonUtils {
    public static void main(String[] args) {
        String s = "[[\"2021.09.08\",\"08:30-18:00\",\"啥也不干\"],[\"2021.09.09\",\"08:30-18:00\",\"干呆着\"]]";
        List<List> list = JSON.parseArray(s, List.class);
        System.out.println(list);
        System.out.println(convertObjToStr(list));
    }

    public static String convertObjToStr(Object obj) {
        return JSON.toJSONString(obj, true);
    }


    public static Student convertStrToObj(String str) {
        return JSON.parseObject(str, Student.class);
    }

}
