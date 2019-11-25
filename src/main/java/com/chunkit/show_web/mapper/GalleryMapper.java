package com.chunkit.show_web.mapper;

import com.chunkit.show_web.entity.Gallery;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GalleryMapper extends Mapper<Gallery> {

    @Select("select * from gallery")
    public List<Gallery> getAll();

}