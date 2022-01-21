package com.llys.service;

import com.llys.annotion.Test;
import com.llys.annotion.TestChildren;
import com.llys.data.Student;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resources;

/**
 * 反射测试类
 *
 * @author liyongsheng
 * @date 2021/12/29 11:24
 **/
@Resources({})
//@Test(value = "123", age = 10)
@TestChildren
public class ReflectTestService {

    private static final String finalStr = "123";

    private Student student;

    @Test(value = "123", age = 10)
    @Getter
    private String name;

    private int age;

    public ReflectTestService(String name) {
        this.name = name;
    }

    public static Long getCurrentTime() {
        return System.currentTimeMillis();
    }

    private static Long privateStaticMethod() {
        return System.currentTimeMillis();
    }

    public Long publicMethod(){
        return System.currentTimeMillis();
    }

    private Long privateMethod(){
        return System.currentTimeMillis();
    }
}
