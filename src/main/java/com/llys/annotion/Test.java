package com.llys.annotion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义一个简单注解
 * @author liyongsheng
 * @date 2021/12/29 11:09
 **/
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD}) //声明应用在属性上
@Retention(RetentionPolicy.RUNTIME) //运行期生效
@Documented //Java Doc
@Inherited
public @interface Test {
    String value() default "";
    int age() default 18;
}
