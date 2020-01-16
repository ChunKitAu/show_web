package com.chunkit.show_web.auth.Exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther ChunKitAu
 * @create 2020-01-15 15
 */
@ControllerAdvice
public class ShiroException {

    /**
     * 处理Shiro权限拦截异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Map<String,Object> defaultErrorHandler(){
        Map<String,Object> map = new HashMap<>();
        map.put("403","权限不足");
        return map;
    }

}
