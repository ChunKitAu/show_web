package com.chunkit.show_web.controller;

import com.chunkit.show_web.entity.Gallery;
import com.chunkit.show_web.entity.User;
import com.chunkit.show_web.service.UserService;
import com.chunkit.show_web.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auther ChunKitAu
 * @create 2019-11-13 13
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Msg findAll(){
        return Msg.success(userService.findAll());
    }

    @GetMapping("/{id}")
    public Msg findById(@PathVariable("id")Integer id){
        return Msg.success(userService.findById(id));
    }

    @PostMapping
    public Msg add(User user){
        return  Msg.expect(userService.add(user));
    }

    @DeleteMapping("/{id}")
    public Msg delete(@PathVariable("id")Integer id){
        return Msg.expect(userService.delete(id));
    }

    @PutMapping
    public Msg update(@Valid User user){
        return Msg.expect(userService.update(user));
    }

}