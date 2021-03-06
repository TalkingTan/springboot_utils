package com.talkingtan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@RestController
public class FileUploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    /**
     * 上传文件
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("name") String name,
                            @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                System.out.println(file.getOriginalFilename());
                byte[] bytes = file.getBytes();

                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

}
