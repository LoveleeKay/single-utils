package com.llys.service;

import com.llys.annotion.Test;

public interface UserService {
    @Test(value = "123", age = 10)
    public void select();
    public void update();
}