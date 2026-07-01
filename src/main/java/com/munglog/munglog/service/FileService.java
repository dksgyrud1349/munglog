package com.munglog.munglog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    private static final String UPLOAD_DIR = "/Users/anhyokyung/uploads/dogs/";

    public String upload(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        String originalName = file.getOriginalFilename();

        String extension = originalName.substring(originalName.lastIndexOf("."));

        String fileName = UUID.randomUUID() + extension;

        File saveFile = new File(UPLOAD_DIR + fileName);

        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }

        return "/uploads/dogs/" + fileName;
    }

    public void delete(String imageUrl) {

        if (imageUrl == null || imageUrl.isBlank()) {
            return;
        }

        try {

            // /uploads/dogs/abc.jpg
            // -> abc.jpg
            String fileName =
                    imageUrl.substring(imageUrl.lastIndexOf("/") + 1);

            File file = new File(UPLOAD_DIR + fileName);

            if (file.exists()) {
                file.delete();
            }

        } catch (Exception e) {
            throw new RuntimeException("파일 삭제 실패", e);
        }
    }
}
