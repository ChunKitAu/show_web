package com.chunkit.show_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther ChunKitAu
 * @create 2019-11-17 17
 */
@Controller
public class RedirectController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/article_list")
    public String crud(){
        return "article_list";
    }

    @GetMapping("/index")
    public String index(){
        return "write";
    }

}
