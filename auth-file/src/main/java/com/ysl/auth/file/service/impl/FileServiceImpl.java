package com.ysl.auth.file.service.impl;

import com.ysl.auth.file.config.MinioConfig;
import com.ysl.auth.file.exceptions.FileException;
import com.ysl.auth.file.service.IFileService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author long
 */
@Slf4j
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private String minioBucket;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioBucket)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return String.format("https://%s/%s/%s", minioConfig.getEndpoint(), minioBucket, fileName);

        } catch (Exception e) {
            log.error("上传文件失败", e);
            throw new FileException("-1", "文件上传失败");
        }
    }

    @Override
    public byte[] downloadFile(String fileName) {
        try (InputStream fileStream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(minioBucket)
                        .object(fileName)
                        .build()
        )) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            log.error("上传下载失败", e);
            throw new FileException("-2", "上传下载失败");
        }
    }
}
