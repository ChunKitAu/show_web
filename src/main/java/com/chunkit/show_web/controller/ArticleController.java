package com.chunkit.show_web.controller;

import com.chunkit.show_web.entity.Article;
import com.chunkit.show_web.service.ArticleService;
import com.chunkit.show_web.util.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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
    public Msg findAll(@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        //引入PageHelper分页插件
        //在查询前只需要,传入页码以及分页数
        PageHelper.startPage(pn, 10);
        List<Article> articles = articleService.findAll();
        PageInfo pageInfo = new PageInfo(articles, 5);
        return Msg.success().setData(pageInfo);
    }

    @GetMapping("/getById")
    public Msg findById(@RequestParam("id")Integer id){
        return Msg.success(articleService.findById(id));
    }

    @PostMapping
    public Msg add(Article article){
        article.setTime(new Date());
        return  Msg.expect(articleService.add(article));
    }

    @DeleteMapping("/{id}")
    public Msg delete(@PathVariable("id")Integer id){
        return Msg.expect(articleService.delete(id));
    }

    @PutMapping
    public Msg update(@Valid Article article){
        article.setTime(new Date());
        return Msg.expect(articleService.update(article));
    }


    @GetMapping("/listByType")
    public Msg getArticleByType(@RequestParam("type") String type,@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        //引入PageHelper分页插件
        //在查询前只需要,传入页码以及分页数
        PageHelper.startPage(pn, 10);
        List<Article> articles = articleService.getArticleByType(type);
        PageInfo pageInfo = new PageInfo(articles, 5);
        return Msg.success().setData(pageInfo);
    }


    @GetMapping("/getIdByType")
    public Msg getIdByType(@RequestParam("type") String type){
        return Msg.success(articleService.getIdByType(type));
    }


}
