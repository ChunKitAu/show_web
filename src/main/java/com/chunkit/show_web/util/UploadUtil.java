package com.chunkit.show_web.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2019-11-12 12
 */
public class UploadUtil {

    /**
     * 文件上传
     * @param request
     * @param DirectoryName
     * @param suffix 文件的后缀
     * @return
     */
    public static String upload(HttpServletRequest request, String DirectoryName, String suffix, MultipartFile file) throws IOException {
        // 获得上传路径的绝对路径地址(/upload)-->
        String realPath = request.getSession().getServletContext().getRealPath("/" + DirectoryName);

        // 如果路径不存在，则创建该路径
        File realPathDirectory = new File(realPath);
        if (realPathDirectory == null || !realPathDirectory.exists()) {
            realPathDirectory.mkdirs();
        }

        // 重命名上传后的文件名 111112323.jpg
        String fileName = System.currentTimeMillis()+suffix;

        // 定义上传路径 .../upload/111112323.jpg
        File uploadFile = new File(realPathDirectory + "\\" + fileName);
        System.out.println(uploadFile);

        file.transferTo(uploadFile);
        return  fileName;
    }

}
