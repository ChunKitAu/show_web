package com.chunkit.show_web.service.impl;

import com.chunkit.show_web.entity.Article;
import com.chunkit.show_web.mapper.ArticleMapper;
import com.chunkit.show_web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2019-11-12 12
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final static int SUCCESS = 1;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Boolean add(Article article) {
        if(articleMapper.insert(article) ==SUCCESS) return true;
        else return false;
    }

    @Override
    public Boolean delete(Integer id) {
        if(articleMapper.deleteByPrimaryKey(id) == SUCCESS) return true;
        return false;
    }

    @Override
    public Boolean update(Article article) {
        if(articleMapper.updateByPrimaryKey(article) == SUCCESS) return true;
        return false;
    }

    @Override
    public Article findById(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Article> findAll() {
        return articleMapper.selectAll();
    }

    @Override
    public List<Article> getArticleByType(String type) {
        return articleMapper.getArticleByType(type);
    }

    @Override
    public int getIdByType(String type) {
        return articleMapper.getIdByTpe(type);
    }


}
