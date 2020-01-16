package com.chunkit.show_web.auth.mapper;

import com.chunkit.show_web.auth.entity.Role_Power;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface Role_PowerMapper extends Mapper<Role_Power> {


    /**
     * 根据roleId查询对应的powerId
     * @param roleId 角色id
     * @return 权限id
     */
    @Select("SELECT * FROM role_power WHERE roleId = #{roleId}")
    List<Integer> getPowerByRoleId(Integer roleId);

}