package com.chunkit.show_web.controller;

import com.chunkit.show_web.entity.Gallery;
import com.chunkit.show_web.service.GalleryService;
import com.chunkit.show_web.util.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public Msg findAll(@RequestParam(value = "pn", defaultValue = "1") Integer pn){

        PageHelper.startPage(pn, 9);
        List<Gallery> gallerys = galleryService.findAll();
        //PageHelper
        PageInfo pageInfo = new PageInfo(gallerys, 5);
        return Msg.success().setData(pageInfo);
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


    @PutMapping("/updateById")
    public Msg updateByid(@RequestParam("select")int select,@RequestParam("id")int id){
        return Msg.expect(galleryService.updateById(id,select));
    }

    @GetMapping("/isSelect")
    public Msg getByisSelect(){
        return Msg.success(galleryService.getBySelect());
    }
}
