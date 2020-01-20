package com.chunkit.show_web.controller;

import com.chunkit.show_web.entity.Gallery;
import com.chunkit.show_web.service.GalleryService;
import com.chunkit.show_web.util.Msg;
import com.chunkit.show_web.util.UploadUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @auther ChunKitAu
 * @create 2019-11-11 11
 */
@RequiresRoles("admin")
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

    /**
     * ckeditor上传图片
     * @param request
     * @return
     */
    @RequestMapping("uploadImage")
    @ResponseBody
    public String imageUpload(HttpServletRequest request) {
        String DirectoryName = "/images/";

        return UploadUtil.upload(request,imageTypes,DirectoryName);
    }


    /**
     * ckeditor 上传视频
     * @param request
     * @return
     */
    @RequestMapping("uploadVdeio")
    @ResponseBody
    public String vedioUpload(HttpServletRequest request) {
        String DirectoryName = "/vedios/";
        return UploadUtil.upload(request,vedioTypes,DirectoryName);
    }

    /**
     * 上传画廊的图片
     * @param request
     * @param file 前端上传的图片
     * @return
     */
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
            InputStream inputStream = file.getInputStream();
            String s = UploadUtil.uploadFile(DirectoryName,inputStream,suffix);

            if(s == null){
                return Msg.failure().setMessage("上传失败");
            }
             gallery.setImg("http://120.25.237.83:8091/upload/gallery/"+s);
             gallery.setIsSelect(0);
             return Msg.expect(galleryService.add(gallery));
        } catch (IOException e) {
            e.printStackTrace();
            return Msg.failure().setMessage("上传失败");
        }
    }

    /**
     * ckeditor 复制图片上传 的一个方案    先将图片转为base64格式 然后在书写过程上传
     * 目前 没使用
     * @param request
     * @param response
     * @return
     */
//    @RequestMapping(value = "uploadImageByPase",method = RequestMethod.POST)
    public Msg uploadImageByPase(HttpServletRequest request,HttpServletResponse response){
        //图片保存路径
        String DirectoryName = "/images/";
        String fileName = null;
        Map<String,Object> map = new HashMap<>();

        try {
            String src=request.getParameter("src");
            if(src==null || src.trim().length()==0){
                Msg.failure();
            }else{
                String[] srcArr = src.split(";base64,");//data:image/png;base64,iVBORw0KGgoAA
                //文件后缀
                String fileSuffixes = srcArr[0].split("/")[1];//data:image/png

                BASE64Decoder decoder = new BASE64Decoder();

                byte[] b = decoder.decodeBuffer(srcArr[1]);
                for(int i=0;i<b.length;++i){
                    if(b[i]<0){//调整异常数据
                        b[i]+=256;
                    }
                }
                //将base64 转为输入流
                InputStream inputStream = new ByteArrayInputStream(b);
                fileName = UploadUtil.uploadFile(DirectoryName, inputStream, fileSuffixes);

                if(fileName == null){
                     return Msg.failure();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.failure();
        }

        map.put("url","http://120.25.237.83:8091/upload"+DirectoryName+fileName);
        return Msg.success().setData(map);
    }



}
