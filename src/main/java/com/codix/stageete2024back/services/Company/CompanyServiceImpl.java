package com.codix.stageete2024back.services.Company;

import com.codix.stageete2024back.DTO.AdDTO;
import com.codix.stageete2024back.DTO.ReservationDTO;
import com.codix.stageete2024back.Entity.Ad;
import com.codix.stageete2024back.Entity.Reservation;
import com.codix.stageete2024back.Entity.ReservationStatus;
import com.codix.stageete2024back.Entity.User;
import com.codix.stageete2024back.Repository.AdRepository;
import com.codix.stageete2024back.Repository.ReservationRepository;
import com.codix.stageete2024back.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl  implements CompanyService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public boolean postAd(Long userId, AdDTO adDTO) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Ad ad = new Ad();
            ad.setServiceName(adDTO.getServiceName());
            ad.setDescription(adDTO.getDescription());
            ad.setImg(adDTO.getImg() != null ? adDTO.getImg().getBytes() : null);
            ad.setPrice(adDTO.getPrice());
            ad.setUser(optionalUser.get());

            adRepository.save(ad);
            return true;
        }
        return false;
    }


    public List<AdDTO> getAllAds(Long userId) {
        return adRepository.findAllByUserId(userId).stream()
                .map(ad -> {
                    AdDTO adDTO = ad.getAdDTO();
                    adDTO.setRetrnedImg(ad.getImg());
                    return adDTO;
                })
                .collect(Collectors.toList());
    }

    public AdDTO getAdById(Long adId) {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()) {
            AdDTO adDTO = optionalAd.get().getAdDTO();
            if (adDTO.getImg() != null) {
                adDTO.setRetrnedImg(Base64.getEncoder().encodeToString(optionalAd.get().getImg()).getBytes());
            }
            return adDTO;
        }
        return null;
    }

    public boolean updateAd(Long adId, AdDTO adDTO) throws IOException {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()){
            Ad ad = optionalAd.get();

            ad.setServiceName(adDTO.getServiceName());
            ad.setDescription(adDTO.getDescription());
            ad.setPrice(adDTO.getPrice());

            if (adDTO.getImg() != null){
                ad.setImg(adDTO.getImg().getBytes());
            }

            adRepository.save(ad);
            return true;
        }else {
            return false ;
        }
    }

    public boolean deleteAd(Long adId) {
        System.out.println("Attempting to delete ad with ID: " + adId); // Ajout du log
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()) {
            adRepository.delete(optionalAd.get());
            return true;
        }
        return false;
    }

    public List<ReservationDTO> getAllAdBookings(Long companyId){
        return reservationRepository.findAllByCompanyId(companyId)
                .stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    public boolean changeBookingStatus(Long bookingId, String status){
        Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
        if (optionalReservation.isPresent()){
            Reservation existingReservation = optionalReservation.get();
            if (Objects.equals(status, "Approve")){
                existingReservation.setReservationStatus(ReservationStatus.APPROVED);
            }else {
                existingReservation.setReservationStatus(ReservationStatus.REJECTED);
            }

            reservationRepository.save(existingReservation);
            return true;
        }
        return false;
    }


}
