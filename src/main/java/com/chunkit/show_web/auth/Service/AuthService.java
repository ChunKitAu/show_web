package com.chunkit.show_web.auth.Service;

import com.chunkit.show_web.auth.entity.Power;
import com.chunkit.show_web.auth.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * @auther ChunKitAu
 * @create 2020-01-15 15
 */
public interface AuthService {

    /**
     * 根据用户id获取该用户对应的角色id
     * @param userId 用户id
     * @return 角色id
     */
    List<Integer> getRoleIdByUserId(int userId);

    /**
     * 根据角色id获取改角色对应的权限id
     * @param rolerId 角色id
     * @return 权限id
     */
    List<Integer> getPowerIdByRoleId(int rolerId);

    /**
     * 根据角色id获取角色
     * @param roleId 角色id
     * @return 角色
     */
    Role getRoleByRoleId(int roleId);

    /**
     * 根据权限id获取权限
     * @param powerId 权限id
     * @return 权限
     */
    Power getPowerByPowerId(int powerId);

}
