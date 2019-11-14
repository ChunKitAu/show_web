package com.chunkit.show_web.entity;

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