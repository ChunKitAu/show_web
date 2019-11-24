package com.chunkit.show_web.mapper;

import com.chunkit.show_web.entity.Article;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {

    @Select("SELECT * FROM article WHERE TYPE = #{type}  ORDER BY TIME DESC")
    List<Article> getArticleByType(String type);

}