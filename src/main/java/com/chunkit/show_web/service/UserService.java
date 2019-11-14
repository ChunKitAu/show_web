package com.chunkit.show_web.service;

import com.chunkit.show_web.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2019-11-12 12
 */
public interface UserService {
    /**
     * 添加
     * @param user
     * @return
     */
    Boolean add(User user);


    /**
     * 删除
     * @param id
     * @return
     */
    Boolean delete(Integer id);

    /**
     * 修改
     * @param user
     * @return
     */
    Boolean update(User user);


    /**
     * 查询单条数据
     * @param id
     * @return
     */
    User findById(Integer id);


    /**
     * 查询全部数据
     * @return
     */
    List<User> findAll();


    User findUserByAccountName(String AccountName);
}
