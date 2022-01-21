package com.llys.impl;

import com.llys.service.AbstractClass;

/**
 * 抽象类实现类
 *
 * @author liyongsheng
 * @date 2022/01/18 10:08
 **/
public class AbstractClassImpl extends AbstractClass {
    @Override
    protected String getName() {
        return "Instance Class";
    }
}
