package com.chunkit.show_web.auth.Service;

import com.chunkit.show_web.auth.entity.Power;
import com.chunkit.show_web.auth.entity.Role;
import com.chunkit.show_web.auth.mapper.PowerMapper;
import com.chunkit.show_web.auth.mapper.RoleMapper;
import com.chunkit.show_web.auth.mapper.Role_PowerMapper;
import com.chunkit.show_web.auth.mapper.User_RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2020-01-15 15
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    User_RoleMapper user_roleMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    Role_PowerMapper role_powerMapper;

    @Autowired
    PowerMapper powerMapper;


    @Override
    public List<Integer> getRoleIdByUserId(int userId) {
        return user_roleMapper.getRoleIdByUserId(userId);
    }

    @Override
    public List<Integer> getPowerIdByRoleId(int rolerId) {
        return role_powerMapper.getPowerByRoleId(rolerId);
    }

    @Override
    public Role getRoleByRoleId(int roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public Power getPowerByPowerId(int powerId) {
        return powerMapper.selectByPrimaryKey(powerId);
    }

}
