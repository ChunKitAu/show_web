package com.chunkit.show_web.controller;

import com.chunkit.show_web.entity.Gallery;
import com.chunkit.show_web.service.GalleryService;
import com.chunkit.show_web.util.Msg;
import com.chunkit.show_web.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2019-11-11 11
 */
@Controller
public class UploadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    GalleryService galleryService;

    // 图片类型
    private static List<String> imageTypes = new ArrayList<String>();
    //视频
    private static List<String> vedioTypes = new ArrayList<String>();

    static {
        imageTypes.add(".jpg");
        imageTypes.add(".jpeg");
        imageTypes.add(".bmp");
        imageTypes.add(".gif");
        imageTypes.add(".png");

        vedioTypes.add(".mp4");
    }

    @RequestMapping("uploadImage")
    @ResponseBody
    public String imageUpload(HttpServletRequest request) {
        String DirectoryName = "/images/";

        return UploadUtil.upload(request,imageTypes,DirectoryName);
    }

    //上传视频
    @RequestMapping("uploadVdeio")
    @ResponseBody
    public String vedioUpload(HttpServletRequest request) {
        String DirectoryName = "/vedios/";
        return UploadUtil.upload(request,vedioTypes,DirectoryName);
    }


    @RequestMapping(value = "uploadGallery",method = RequestMethod.POST)
    @ResponseBody
    public Msg galleryImageUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String DirectoryName = "/gallery/";
        //获取后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();

        if(!imageTypes.contains(suffix)){
            return Msg.failure().setMessage("文件格式错误");
        }

        Gallery gallery = new Gallery();

        try {
            String s = UploadUtil.uploadFile(request, DirectoryName,file);

            if(s == null){
                return Msg.failure().setMessage("上传失败");
            }
             gallery.setImg("http://10.0.57.28/gallery/"+s);
             gallery.setIsSelect(0);
             return Msg.expect(galleryService.add(gallery));
        } catch (IOException e) {
            e.printStackTrace();
            return Msg.failure().setMessage("上传失败");
        }
    }

}
