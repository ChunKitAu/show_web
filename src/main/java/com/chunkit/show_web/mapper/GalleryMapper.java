package com.chunkit.show_web.mapper;

import com.chunkit.show_web.entity.Gallery;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GalleryMapper extends Mapper<Gallery> {

    @Select("select * from gallery")
    List<Gallery> getAll();

    //通用mapper updateByid失效   原因不详
    @Update("update gallery set isSelect = #{isSelect} where id = #{id}")
    boolean updateById(int id,int isSelect);



    @Select("select * from gallery where isSelect = 1")
    List<Gallery> getBySelect();

}