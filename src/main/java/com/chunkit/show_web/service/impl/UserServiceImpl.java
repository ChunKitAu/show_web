package com.chunkit.show_web.service.impl;

import com.chunkit.show_web.entity.User;
import com.chunkit.show_web.mapper.UserMapper;
import com.chunkit.show_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2019-11-12 12
 */
@Service
public class UserServiceImpl implements UserService {

    private final static int SUCCESS = 1;

    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean add(User user) {
        if(userMapper.insert(user) == SUCCESS) return  true;
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        if(userMapper.deleteByPrimaryKey(id) == SUCCESS) return true;
        return false;
    }

    @Override
    public Boolean update(User user) {
        if(userMapper.updateByPrimaryKey(user) == SUCCESS)return  true;
        return false;
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public User findUserByAccountName(String accountName) {
        return userMapper.findUserByAccountName(accountName);
    }


}
