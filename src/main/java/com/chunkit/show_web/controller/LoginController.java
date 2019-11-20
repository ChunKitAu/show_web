package com.chunkit.show_web.controller;

import com.chunkit.show_web.util.Msg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @auther ChunKitAu
 * @create 2019-11-14 14
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    public Msg login(@RequestParam("accountName")String accountName,@RequestParam("password")String password){

        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountName,password);

        try{
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            e.printStackTrace();
            return Msg.failure("账号或密码错误");
        }catch (AuthorizationException e){
            e.printStackTrace();
            return Msg.failure("没有权限");
        }

        return Msg.success();
    }

    /**
     * 未登陆
     * @return
     */
//    @RequestMapping(value = "/login")
//    public Msg unauth() {
//        return Msg.failure().setCode(401).setMessage("未登录");
//    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout")
    public Msg logout() {
        Subject lvSubject = SecurityUtils.getSubject();
        lvSubject.logout();
        return Msg.success().setMessage("退出成功");
    }

}
