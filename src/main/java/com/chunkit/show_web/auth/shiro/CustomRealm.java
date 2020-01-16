package com.chunkit.show_web.auth.shiro;

import com.chunkit.show_web.auth.Service.AuthService;
import com.chunkit.show_web.entity.User;
import com.chunkit.show_web.service.UserService;
import com.chunkit.show_web.auth.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @auther ChunKitAu
 * @create 2019-11-14 14
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;
    /**
     * 授权权限
     * 用户进行权限验证时候Shiro会去缓存中找,如果查不到数据,会执行这个方法去查权限,并放入缓存中
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        //获取用户id
        Integer userId = user.getUserid();

        Set<String> roleSet = new HashSet<>();
        Set<String> powerSet = new HashSet<>();
        //查询角色和权限
        final List<Integer> roles = authService.getRoleIdByUserId(userId);
        for (Integer roleId:roles) {
            roleSet.add(authService.getRoleByRoleId(roleId).getRolename());
            List<Integer> powers = authService.getPowerIdByRoleId(roleId);
            for (Integer powerId:powers) {
                powerSet.add(authService.getPowerByPowerId(powerId).getPowername());
            }
        }
        //将权限和角色传入到SimpleAuthorizationInfo 中
        simpleAuthorizationInfo.setStringPermissions(powerSet);
        simpleAuthorizationInfo.setRoles(roleSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 获取用户信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;

        if(authenticationToken.getPrincipal() == null) return null;

        //获取用户信息
        String accountName = authenticationToken.getPrincipal().toString();

        User user = userService.findUserByAccountName(accountName);

        if(user == null) throw new UnknownAccountException();

        //盐值.
        ByteSource salt = ByteSource.Util.bytes(accountName);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());

        //验证成功开始踢人(清除缓存和Session)
        ShiroUtils.deleteCache(accountName,true);
        return simpleAuthenticationInfo;
    }
}
