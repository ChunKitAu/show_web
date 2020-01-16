package com.chunkit.show_web.auth.util;

import com.chunkit.show_web.entity.User;
import com.chunkit.show_web.util.SpringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.Collection;
import java.util.Objects;

/**
 * Shiro 工具类
 * @auther ChunKitAu
 * @create 2020-01-15 15
 */
public class ShiroUtils {

    private ShiroUtils(){};

    private static RedisSessionDAO redisSessionDAO = SpringUtil.getBean(RedisSessionDAO.class);

    /**
     * 用户登出
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取当前用户的Session
     * @return
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public static User getLoginUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 动态权限加载更新
     * 删除用户缓存信息
     * @param accountName
     * @param isRemoveSession
     */
    public static void deleteCache(String accountName, boolean isRemoveSession){
        //从缓存中获取Session
        Session session = null;
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        User user;
        Object attribute = null;
        for (Session sessionUser:sessions) {
            //遍历Session,找到该用户名称对应的Session
            attribute = sessionUser.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(attribute == null){
                continue;
            }
            user = (User)((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
            if(user == null){
                continue;
            }
            if(Objects.equals(user.getAccountname(),accountName)){
                session = sessionUser;
                break;
            }
        }
        if(session == null||attribute == null)
            return;
        //清除该用户以前登录时保存的session，强制退出  -> 单用户登录处理
        if(isRemoveSession){
            redisSessionDAO.delete(session);
        }
        //删除Cache，在访问受限接口时会重新授权
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        Authenticator authc = securityManager.getAuthenticator();
        ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);
    }



}
