package com.chunkit.show_web.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.chunkit.show_web.util.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @auther ChunKitAu
 * @create 2019-11-11 11
 */
@Controller
public class UploadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 图片类型
    private static List<String> imageTypes = new ArrayList<String>();
    //视频
    private static List<String> vedioTypes = new ArrayList<String>();

    private Map<String,Object> map = new HashMap<>();
    //上传后定义的文件名
    private String fileName = null;

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
    public String imageUpload(HttpServletRequest request, HttpServletResponse response) {
        String DirectoryName = "upload/images/";

        try {
            logger.info("上传的路径："+ request.getSession().getServletContext().getRealPath("/" + DirectoryName));

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> fileNames = ((MultipartHttpServletRequest) request).getFileNames();
            while(fileNames.hasNext()){
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(fileNames.next());

                if(file!=null){
                    // 取得当前上传文件的文件名称
                    String originalFilename = file.getOriginalFilename();
                    System.out.println(originalFilename);
                    // 获得图片后缀名称,如果后缀不为图片格式，则不上传
                    String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();

                    if(imageTypes.contains(suffix)){
                        fileName = UploadUtil.upload(request, DirectoryName, suffix, file);
                    }else{
                        //文件格式错误
                        map.put("uploaded",0);
                        Map<String ,String> error = new HashMap<>();
                        error.put("message","文件格式错误");
                        map.put("error",error);
                        return JSONUtils.toJSONString(map);
                    }
                }else {
                    //无文件
                    map.put("uploaded",0);
                    Map<String ,String> error = new HashMap<>();
                    error.put("message","上传失败");
                    map.put("error",error);
                    return JSONUtils.toJSONString(map);
                }
            }
            //实现图片回显
            map.put("uploaded",1);
            map.put("fileName",fileName);
            map.put("url",DirectoryName+fileName);
            return JSONUtils.toJSONString(map);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("uploaded",0);
        Map<String ,String> error = new HashMap<>();
        error.put("message","上传失败");
        map.put("error",error);
        return JSONUtils.toJSONString(map);
    }

    //上传视频
    @RequestMapping("uploadVdeio")
    @ResponseBody
    public String vedioUpload(HttpServletRequest request, HttpServletResponse response) {
        String DirectoryName = "upload/vedios/";
        try {
            logger.info("上传的路径："+ request.getSession().getServletContext().getRealPath("/" + DirectoryName));

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> fileNames = ((MultipartHttpServletRequest) request).getFileNames();
            while(fileNames.hasNext()){
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(fileNames.next());

                if(file!=null){
                    // 取得当前上传文件的文件名称
                    String originalFilename = file.getOriginalFilename();
                    System.out.println(originalFilename);
                    // 获得图片后缀名称,如果后缀不为图片格式，则不上传
                    String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();

                    if(vedioTypes.contains(suffix)){
                        fileName = UploadUtil.upload(request, DirectoryName, suffix, file);
                    }else{
                        //文件格式错误
                        map.put("uploaded",0);
                        Map<String ,String> error = new HashMap<>();
                        error.put("message","文件格式错误");
                        map.put("error",error);
                        return JSONUtils.toJSONString(map);
                    }
                }else {
                    //无文件
                    map.put("uploaded",0);
                    Map<String ,String> error = new HashMap<>();
                    error.put("message","上传失败");
                    map.put("error",error);
                    return JSONUtils.toJSONString(map);
                }
            }
            //实现回显
            map.put("uploaded",1);
            map.put("fileName",fileName);
            map.put("url",DirectoryName+fileName);

            return JSONUtils.toJSONString(map);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("uploaded",0);
        Map<String ,String> error = new HashMap<>();
        error.put("message","上传失败");
        map.put("error",error);
        return JSONUtils.toJSONString(map);
    }


}
