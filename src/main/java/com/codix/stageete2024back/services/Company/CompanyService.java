package com.codix.stageete2024back.services.Company;

import com.codix.stageete2024back.DTO.AdDTO;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
    boolean postAd(Long userId, AdDTO adDTO) throws IOException;

    List<AdDTO> getAllAds(Long userId);
}
