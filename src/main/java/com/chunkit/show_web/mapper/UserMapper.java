package com.chunkit.show_web.mapper;

import com.chunkit.show_web.entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {

    @Select("select * from user where accountName = #{AccountName}")
    public User findUserByAccountName(String AccountName);
}