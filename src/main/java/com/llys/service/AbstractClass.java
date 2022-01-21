package com.llys.service;

/**
 * 继承方法测试基类
 *
 * @author liyongsheng
 * @date 2022/01/18 10:04
 **/
public abstract class AbstractClass {

    protected String getName() {
        return "AbstractClass";
    }

    public void printName() {
        test();
    }

    private void test() {
        System.out.println("name = " + getName());
    }
}
