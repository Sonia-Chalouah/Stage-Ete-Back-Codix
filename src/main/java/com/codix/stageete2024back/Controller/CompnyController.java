package com.codix.stageete2024back.Controller;

import com.codix.stageete2024back.DTO.AdDTO;
import com.codix.stageete2024back.services.Company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/company")
public class CompnyController {
    @Autowired
    private CompanyService companyService;


    @PostMapping("/ad/{userId}")
    public ResponseEntity<?> postAd(@PathVariable Long userId, @ModelAttribute AdDTO adDTO) throws IOException {
        boolean success = companyService.postAd(userId, adDTO);
        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
