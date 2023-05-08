package com.example.equipmentmanagementspring.deviceConfig.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileIO{



    public String uploadFile(String filePath, MultipartFile file) throws Exception{
        try {
            String fileName = file.getOriginalFilename();
            File f = new File(filePath + fileName);
            file.transferTo(f);
            fileName = filePath + fileName;
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            fileInputStream.close();
            return fileName;
        }catch (Exception e){

            e.printStackTrace();
            return "fail";
        }
    }

    public void DeleteFileDir(Path path) throws IOException {
        Files.walkFileTree(path,
                new SimpleFileVisitor<Path>() {
                    // 先去遍历删除文件
                    @Override
                    public FileVisitResult visitFile(Path file,
                                                     BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        System.out.printf("文件被删除 : %s%n", file);
                        return FileVisitResult.CONTINUE;
                    }
                    // 再删除目录
                    @Override
                    public FileVisitResult postVisitDirectory(Path dir,
                                                              IOException exc) throws IOException {
                        Files.delete(dir);
                        System.out.printf("文件夹被删除: %s%n", dir);
                        return FileVisitResult.CONTINUE;
                    }

                }
        );

    }


}
