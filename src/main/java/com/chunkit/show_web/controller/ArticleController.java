package com.chunkit.show_web.controller;

import com.chunkit.show_web.entity.Article;
import com.chunkit.show_web.service.ArticleService;
import com.chunkit.show_web.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auther ChunKitAu
 * @create 2019-11-13 13
 */
@RequestMapping("/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public Msg findAll(){
        return Msg.success(articleService.findAll());
    }

    @GetMapping("/{id}")
    public Msg findById(@PathVariable("id")Integer id){
        return Msg.success(articleService.findById(id));
    }

    @PostMapping
    public Msg add(Article article){
        return  Msg.expect(articleService.add(article));
    }

    @DeleteMapping("/{id}")
    public Msg delete(@PathVariable("id")Integer id){
        return Msg.expect(articleService.delete(id));
    }

    @PutMapping
    public Msg update(@Valid Article article){
        return Msg.expect(articleService.update(article));
    }

}
