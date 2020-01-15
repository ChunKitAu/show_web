package com.chunkit.show_web.mapper;

import com.chunkit.show_web.entity.Article;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {

    @Select("SELECT * FROM article WHERE TYPE = #{type}  ORDER BY TIME DESC")
    List<Article> getArticleByType(String type);


    @Select("SELECT id FROM article WHERE TYPE = #{type}")
    int getIdByTpe(String type);

}