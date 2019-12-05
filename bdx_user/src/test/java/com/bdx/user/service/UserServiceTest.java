package com.bdx.user.service;

import com.bdx.user.entity.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author: 磊大大
 * @date: 2019/10/15 16:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findRoleByUserId() {
    }

    @Test
    public void sendSms() {
        userService.sendSms("17633637447");
    }

    @Test
    public void addUserTest() {

        User user = new User();
        user.setUserPhone("15100000020");
        user.setPassword("szwan");
        user.setNickname("小磊测试");
       // user.setUserQq("666666");


    }
}
