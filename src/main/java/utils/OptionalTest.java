package utils;

import data.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class OptionalTest {
    private OptionalTest() {
        super();
    }

    public static void testOptional() {
        Map<Integer, Student> map = new HashMap<>();
        String name = Optional.ofNullable(map.get(1)).map(Student::getName).orElseGet(null);
        System.out.println(name);
    }
}
