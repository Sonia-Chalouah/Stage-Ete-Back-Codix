package com.codix.stageete2024back.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdDTO {

    private  Long id;
    private String serviceName;
    private  String description;
    private  Double price;
    private MultipartFile img;
    private byte[] retrnedImg;
    private  long userId;
    private String companyName;
}
