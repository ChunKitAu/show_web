package com.chunkit.show_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @auther ChunKitAu
 * @create 2019-11-11 11
 */
@RestController
public class PublishController {

    @PostMapping("/uploadData")
    public String Publish(HttpServletRequest request, HttpServletResponse response){

        String s = request.getParameter("editor1");
        System.out.println(s);

        return s;
    }


}
