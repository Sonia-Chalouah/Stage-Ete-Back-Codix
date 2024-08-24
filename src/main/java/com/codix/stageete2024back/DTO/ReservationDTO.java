package com.codix.stageete2024back.DTO;

import com.codix.stageete2024back.Entity.ReservationStatus;
import com.codix.stageete2024back.Entity.ReviewStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {

    private Long Id;
    private Date bookDate;
    private String serviceName;
    private ReservationStatus reservationStatus;
    private ReviewStatus reviewStatus;
    private Long userId;
    private String userName;
    private Long companyId;
    private Long adId;

}
