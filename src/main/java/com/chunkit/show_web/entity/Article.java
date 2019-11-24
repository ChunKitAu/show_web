package com.chunkit.show_web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 时间
     */
    //返回的时间的格式
    @JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")
    private Date time;

    /**
     * 阅读次数
     */
    @Column(name = "readTimes")
    private Integer readtimes;

    /**
     * 转载地址
     */
    private String reblog;

    /**
     * 作者
     */
    private String author;

    /**
     * 文章类型
     */
    private String type;


}