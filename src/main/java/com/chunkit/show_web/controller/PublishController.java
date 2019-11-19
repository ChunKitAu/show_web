package com.chunkit.show_web.controller;

import com.chunkit.show_web.entity.Article;
import com.chunkit.show_web.service.ArticleService;
import com.chunkit.show_web.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther ChunKitAu
 * @create 2019-11-11 11
 */
@RestController
public class PublishController {

    @Autowired
    ArticleService articleService;
    private final  static int SUCCESS = 1;

    @PostMapping("/uploadData")
    public String Publish(HttpServletRequest request, HttpServletResponse response){
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String tran = request.getParameter("reblog");
        String s = request.getParameter("editor1");

        Article a = new Article(null,title,s,null,null,tran,null,type);
        System.out.println(Msg.expect(articleService.add(a)).toString());

        return s;
    }


}
