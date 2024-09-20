package com.ysl.auth.file.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author long
 */
public interface IFileService {


    /**
     * 文件上传
     * @param file
     * @return
     */
    String uploadFile(@RequestParam("file") MultipartFile file);

    /**
     * 文件下载
     * @param fileName
     * @return
     */
    byte[] downloadFile(String fileName);
}
