package com.llys.data;

import com.llys.annotion.Test;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@Test(value = "123", age = 10)
public class Person {
    public String name; // 姓名 公有
    protected String age;   // 年龄 保护
    private String hobby;   // 爱好   私有

    public Person(String name, String age, String hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }

    public Person() {
    }
    public String getHobby() {
        return hobby;
    }
}
