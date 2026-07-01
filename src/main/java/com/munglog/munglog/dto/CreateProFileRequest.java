package com.munglog.munglog.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateProFileRequest {
    private String dogNm;
    private String breed;
    private int age;
    private double weight;
    private MultipartFile profileImgUrl;
}
