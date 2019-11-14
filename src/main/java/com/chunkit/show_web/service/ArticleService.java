package com.chunkit.show_web.service;

import com.chunkit.show_web.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2019-11-12 12
 */
public interface ArticleService {


    /**
     * 添加
     * @param article
     * @return
     */
    Boolean add(Article article);


    /**
     * 删除
     * @param id
     * @return
     */
    Boolean delete(Integer id);

    /**
     * 修改
     * @param article
     * @return
     */
    Boolean update(Article article);


    /**
     * 查询单条数据
     * @param id
     * @return
     */
    Article findById(Integer id);


    /**
     * 查询全部数据
     * @return
     */
    List<Article> findAll();

}
