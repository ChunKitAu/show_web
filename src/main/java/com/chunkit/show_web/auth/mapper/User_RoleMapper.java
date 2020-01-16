package com.chunkit.show_web.auth.mapper;

import com.chunkit.show_web.auth.entity.User_Role;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface User_RoleMapper extends Mapper<User_Role> {

    /**
     * 根据userId查询RoleId
     * @param userId 用户id
     * @return 角色id
     */
    @Select("SELECT * FROM user_role WHERE userId =#{userId}")
    List<Integer> getRoleIdByUserId(Integer userId);

}