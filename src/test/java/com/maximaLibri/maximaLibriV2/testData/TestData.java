package com.maximaLibri.maximaLibriV2.testData;

import com.maximaLibri.maximaLibriV2.model.User;

public class TestData {

    public User getJimmyUser() {
        User jimmyUser = new User();
        jimmyUser.setLocation("testUser");
        jimmyUser.setAge(21);
        jimmyUser.setEmail("jimy@gmail.com");
        jimmyUser.setUsername("jimmyTestUser");
        jimmyUser.setPassword("jimmyTestUser");
        return jimmyUser;
    }
}
