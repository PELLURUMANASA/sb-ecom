package com.ecommerce.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FIleServiceImpl implements FileService {

    @Override
    public String uploadImage(MultipartFile file) throws IOException {

        String projectPath = System.getProperty("user.dir");

        String uploadDir = projectPath + File.separator + "images";


        File folder = new File(uploadDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }


        String originalFileName = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId + originalFileName.substring(originalFileName.lastIndexOf('.'));


        String filePath = uploadDir + File.separator + fileName;

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }


}
