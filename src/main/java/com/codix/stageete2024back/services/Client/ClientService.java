package com.codix.stageete2024back.services.Client;

import com.codix.stageete2024back.DTO.AdDTO;
import com.codix.stageete2024back.DTO.AdDetailsForClientDTO;
import com.codix.stageete2024back.DTO.ReservationDTO;
import com.codix.stageete2024back.DTO.ReviewDTO;

import java.util.List;

public interface ClientService {

    List<AdDTO> getAllAds();

    List<AdDTO> searchAdByName(String name);

    boolean bookService(ReservationDTO reservationDTO);

    AdDetailsForClientDTO getAdDetailsByAdId(Long adId);

    List<ReservationDTO> getAllBookingsByUserId(Long userId);

    Boolean giveReview(ReviewDTO reviewDTO);


}
