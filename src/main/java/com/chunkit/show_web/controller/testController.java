package com.chunkit.show_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther ChunKitAu
 * @create 2019-11-10 10
 */
@Controller
public class testController {

    @GetMapping("/test")
    @ResponseBody
    public String hello(){
        return  "success";
    }




}
