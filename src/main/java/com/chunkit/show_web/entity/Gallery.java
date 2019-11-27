package com.chunkit.show_web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 图片路径
     */
    private String img;

    /**
     * 是否选用
     */
    @Column(name = "isSelect")
    private Integer isSelect;


}