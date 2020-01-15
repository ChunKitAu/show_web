package com.chunkit.show_web.util;

import com.alibaba.druid.support.json.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @auther ChunKitAu
 * @create 2019-11-12 12
 */
public class UploadUtil {

    private final Logger logger = LoggerFactory.getLogger(UploadUtil.class);

    /**
     * 上传文件 并用于ckeditor 的回显
     * @param request
     * @param FileType
     * @param DirectoryName
     * @return
     */
    public static String upload(HttpServletRequest request, List<String> FileType, String DirectoryName){

        Map<String,Object> map = new HashMap<>();
        //上传后的文件名
        String fileName = null ;
        try {

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

                    if(FileType.contains(suffix)){
                        InputStream inputStream = file.getInputStream();
                        fileName = UploadUtil.uploadFile(DirectoryName,inputStream,suffix);
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
            map.put("url","http://120.25.237.83:8091/upload"+DirectoryName+fileName);
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

    /**
     * 文件保存
     * @param DirectoryName
     * @return
     */
    public static String uploadFile(String DirectoryName,InputStream inputStream,String suffix) throws IOException {

        // 重命名上传后的文件名 111112323.jpg
        String fileName = System.currentTimeMillis()+suffix;
        // 通过ftp 上传到服务器中
//        if(!FTPUtil.uploadFile("10.0.57.28",21,"ftpRoot","root1234","/home/ftpRoot/showWeb/upload/",DirectoryName,fileName,inputStream)){
//            return null;
//        }
        if(!FTPUtil.uploadFile("120.25.237.83",8097,"ftpRoot","root1234","/home/ftpRoot/showWeb/upload/",DirectoryName,fileName,inputStream)){
            return null;
        }
        return  fileName;
    }





}
