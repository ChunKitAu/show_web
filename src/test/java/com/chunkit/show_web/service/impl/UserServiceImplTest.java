package com.chunkit.show_web.service.impl;

import com.chunkit.show_web.entity.User;
import com.chunkit.show_web.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther ChunKitAu
 * @create 2019-11-12 12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void add() {
        if(userMapper.insert(new User(null,"s1111","a123","111")) == 1) System.out.println(1);
    }

    @Test
    public void delete() {
        userMapper.deleteByPrimaryKey(3);
    }

    @Test
    public void update() {
        userMapper.updateByPrimaryKey(new User(2,"11","22","33"));
    }

    @Test
    public void findById() {
        System.out.println(userMapper.selectByPrimaryKey(1));
    }

    @Test
    public void findAll() {
        System.out.println(userMapper.selectAll());
    }
}
