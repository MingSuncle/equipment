package com.example.equipmentmanagementspring.deviceConfig.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

public class FileIO{


    public String uploadFile(String filePath, MultipartFile file) throws Exception{
        try {
            String fileName = file.getOriginalFilename();
            File f = new File(filePath + fileName);
            file.transferTo(f);
            fileName = filePath + fileName;

            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            return fileName;
        }catch (Exception e){

            e.printStackTrace();
            return "fail";
        }
    }
}
