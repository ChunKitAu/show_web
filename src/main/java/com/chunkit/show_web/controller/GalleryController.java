package com.chunkit.show_web.controller;

import com.chunkit.show_web.entity.Gallery;
import com.chunkit.show_web.service.GalleryService;
import com.chunkit.show_web.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @auther ChunKitAu
 * @create 2019-11-13 13
 */
@RequestMapping("/Gallery")
@RestController
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    /**
     * 查询所有画廊图片
     * @return
     */
    @GetMapping("/list")
    public Msg findAll(){
        return Msg.success(galleryService.findAll());
    }

    @GetMapping("/{id}")
    public Msg findById(@PathVariable("id")Integer id){
        return Msg.success(galleryService.findById(id));
    }

    @PostMapping
    public Msg add(Gallery gallery){
        return  Msg.expect(galleryService.add(gallery));
    }

    @DeleteMapping("/{id}")
    public Msg delete(@PathVariable("id")Integer id){
        return Msg.expect(galleryService.delete(id));
    }

    @PutMapping
    public Msg update(@Valid Gallery gallery){
        return Msg.expect(galleryService.update(gallery));
    }


}