package com.chunkit.show_web.service;

import com.chunkit.show_web.entity.Gallery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2019-11-13 13
 */
public interface GalleryService {

    /**
     * 添加画廊图片
     * @param gallery
     * @return
     */
    Boolean add(Gallery gallery);

    /**
     * 删除画廊图片
     * @param id
     * @return
     */
    Boolean delete(Integer id);

    /**
     * 更新画廊图片
     * @param gallery
     * @return
     */
    Boolean update(Gallery gallery);

    /**
     * 通过id获取图片
     * @param id
     * @return
     */
    Gallery findById(Integer id);


    /**
     * 获取所有图片
     * @return
     */
    List<Gallery> findAll();


}
