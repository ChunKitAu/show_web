package com.chunkit.show_web.shiro;

import com.chunkit.show_web.entity.User;
import com.chunkit.show_web.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @auther ChunKitAu
 * @create 2019-11-14 14
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 获取用户权限（暂时无实现功能）
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

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

        //这里验证authenticationToken和simpleAuthenticationInfo的信息

        //密码

        //盐值.
        ByteSource salt = ByteSource.Util.bytes(accountName);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(accountName, user.getPassword(), salt, getName());

        return simpleAuthenticationInfo;
    }
}
