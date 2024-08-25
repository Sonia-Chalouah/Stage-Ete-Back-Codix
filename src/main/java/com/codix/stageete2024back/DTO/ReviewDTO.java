package com.codix.stageete2024back.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {

    private Long Id;

    private Date reviewDate;

    private String review;

    private Long rating;

    private Long userId;

    private  Long adId;

    private String clientName;

    private String serviceName;

    private Long bookId;

}
