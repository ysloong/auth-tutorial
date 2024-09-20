package com.ysl.auth.file.controller;

import com.ysl.auth.common.message.BaseResMessage;
import com.ysl.auth.file.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author long
 */
@RestController
public class FileController {

    @Autowired
    private IFileService fileService;

    @PostMapping(value = "/file/upload")
    public BaseResMessage<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return BaseResMessage.success(fileService.uploadFile(file));
    }

    @GetMapping(value = "/file/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam String file) {
        byte[] fileBytes = fileService.downloadFile(file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file);
        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }
}