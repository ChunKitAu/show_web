package com.chunkit.show_web.config;

import com.chunkit.show_web.auth.shiro.CustomRealm;
import com.chunkit.show_web.auth.shiro.ShiroSessionIdGenerator;
import com.chunkit.show_web.auth.shiro.ShiroSessionManager;
import com.chunkit.show_web.auth.util.SHA256Util;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther ChunKit
 * @date 2019/10/27-16:01
 */
@Configuration
public class ShiroConfig {

    private final String CACHE_KEY = "shiro:cache:";
    private final String SESSION_KEY = "shiro:session:";
    private final int EXPIRE = 1800;


    //Redis配置
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;

    /**
     * 开启Shiro-aop注解支持
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * Shiro基础配置
     * Filter工厂，设置对应的过滤条件和跳转条件
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        Map<String, String> map = new HashMap<>();

        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");

        map.put("/login","anon");
        map.put("/article/listByType","anon");
        map.put("/article/getById","anon");
        map.put("/article/getIdByType","anon");
        map.put("/Gallery/isSelect","anon");
        map.put("/uploadImage","anon");
        map.put("/uploadVdeio","anon");

        map.put("/**", "authc");
//        map.put("/**", "anon");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //安全管理器
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义Realm验证
        securityManager.setRealm(myShiroRealm());
        // 自定义Ssession管理
        securityManager.setSessionManager(sessionManager());
        // 自定义Cache实现
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }


    //将自己的验证方式加入容器
    @Bean
    public CustomRealm myShiroRealm() {
        CustomRealm customRealm = new CustomRealm();
        //加密
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    /**
     * 凭证匹配器
     * 将密码校验交给Shiro的SimpleAuthenticationInfo进行处理,在这里做匹配配置
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher myHashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:使用SHA256算法
        myHashedCredentialsMatcher.setHashAlgorithmName(SHA256Util.HASH_ALGORITHM_NAME);
        //加密次数
        myHashedCredentialsMatcher.setHashIterations(SHA256Util.HASH_ITERATIONS);
        return myHashedCredentialsMatcher;
    }


    /**
     * 配置redis管理器
     * @return
     */
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setPassword(password);
        redisManager.setTimeout(timeout);
        return  redisManager;
    }

    /**
     * 配置Cache管理器
     * 用于往Redis存储权限和角色标识
     * 使用shiro-redis插件
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setKeyPrefix(CACHE_KEY);
        //配置缓存的话要求放在session里面的实体类中必须要有个id标识
        redisCacheManager.setPrincipalIdFieldName("userid");
        return  redisCacheManager;
    }

    /**
     * sessionID生成器
     * @return
     */
    @Bean
    public ShiroSessionIdGenerator sessionIdGenerator(){
        return new ShiroSessionIdGenerator();
    }

    /**
     * 配置RedisSessionDao
     * 使用shiro-redis 插件
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        redisSessionDAO.setKeyPrefix(SESSION_KEY);
        redisSessionDAO.setExpire(EXPIRE);
        return redisSessionDAO;
    }

    /**
     * 配置session管理器
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
        shiroSessionManager.setSessionDAO(redisSessionDAO());
        return shiroSessionManager;
    }






}
