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
        return "crud_list";
    }

    @GetMapping("/gallery")
    public String gallery(){
        return "crud_gallery";
    }


    @GetMapping("/write")
    public String write(){
        return "crud_write";
    }


    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/index_article")
    public String index_article(){
        return "index_article";
    }

    @GetMapping("/list")
    public String list(){
        return "index_list";
    }

    @GetMapping("/introduce")
    public String show(){
        return "index_introduce";
    }

}
