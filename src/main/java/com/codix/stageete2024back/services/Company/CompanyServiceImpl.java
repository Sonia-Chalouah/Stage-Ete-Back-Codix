package com.codix.stageete2024back.services.Company;

import com.codix.stageete2024back.DTO.AdDTO;
import com.codix.stageete2024back.Entity.Ad;
import com.codix.stageete2024back.Entity.User;
import com.codix.stageete2024back.Repository.AdRepository;
import com.codix.stageete2024back.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CompanyServiceImpl  implements CompanyService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    public boolean postAd(Long userId, AdDTO adDTO) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){

            Ad ad = new Ad();
            ad.setServiceName(adDTO.getServiceName());
            ad.setDescription(adDTO.getDescription());
            ad.setImg(adDTO.getImg().getBytes());
            ad.setPrice(ad.getPrice());
            ad.setUser(optionalUser.get());

            adRepository.save(ad);
            return true;
        }
        return false;

    }
}
