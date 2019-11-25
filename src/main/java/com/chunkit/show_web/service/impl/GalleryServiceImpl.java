package com.chunkit.show_web.service.impl;

import com.chunkit.show_web.entity.Gallery;
import com.chunkit.show_web.mapper.GalleryMapper;
import com.chunkit.show_web.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2019-11-13 13
 */
@Service
public class GalleryServiceImpl implements GalleryService {

    final  static Integer SUCCESS = 1;

    @Autowired
    GalleryMapper galleryMapper;

    @Override
    public Boolean add(Gallery gallery) {
        if(galleryMapper.insert(gallery) == SUCCESS) return true;
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        if(galleryMapper.deleteByPrimaryKey(id) == SUCCESS) return true;
        return false;
    }

    @Override
    public Boolean update(Gallery gallery) {
        if(galleryMapper.updateByPrimaryKey(gallery) == SUCCESS) return true;
        return false;
    }

    @Override
    public Gallery findById(Integer id) {
        return galleryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Gallery> findAll() {
        return galleryMapper.getAll();
    }
}
