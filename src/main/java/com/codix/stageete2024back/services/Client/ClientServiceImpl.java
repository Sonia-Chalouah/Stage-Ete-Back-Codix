package com.codix.stageete2024back.services.Client;

import com.codix.stageete2024back.DTO.AdDTO;
import com.codix.stageete2024back.DTO.AdDetailsForClientDTO;
import com.codix.stageete2024back.DTO.ReservationDTO;
import com.codix.stageete2024back.DTO.ReviewDTO;
import com.codix.stageete2024back.Entity.*;
import com.codix.stageete2024back.Repository.AdRepository;
import com.codix.stageete2024back.Repository.ReservationRepository;
import com.codix.stageete2024back.Repository.ReviewRepository;
import com.codix.stageete2024back.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<AdDTO> getAllAds() {
        return adRepository.findAll().stream().map(Ad::getAdDTO).collect(Collectors.toList());
    }

    @Override
    public List<AdDTO> searchAdByName(String name) {
        return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDTO).collect(Collectors.toList());
    }

    @Override
    public boolean bookService(ReservationDTO reservationDTO) {
        Optional<Ad> optionalAd = adRepository.findById(reservationDTO.getAdId());
        Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());

        if (optionalAd.isPresent() && optionalUser.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setBookDate(reservationDTO.getBookDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setUser(optionalUser.get());
            reservation.setAd(optionalAd.get());
            reservation.setCompany(optionalAd.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FALSE);

            reservationRepository.save(reservation);
            return true;
        }

        return false;
    }

    @Override
    public AdDetailsForClientDTO getAdDetailsByAdId(Long adId) {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        AdDetailsForClientDTO adDetailsForClientDTO = new AdDetailsForClientDTO();
        if (optionalAd.isPresent()) {
            adDetailsForClientDTO.setAdDTO(optionalAd.get().getAdDTO());
        }
        return adDetailsForClientDTO;
    }

    @Override
    public List<ReservationDTO> getAllBookingsByUserId(Long userId) {
        return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    @Override
    public Boolean giveReview(ReviewDTO reviewDTO) {
        Optional<User> optionalUser = userRepository.findById(reviewDTO.getUserId());
        Optional<Reservation> optionalReservation = reservationRepository.findById(reviewDTO.getBookId());

        if (optionalUser.isPresent() && optionalReservation.isPresent()) {
            Review review = new Review();
            review.setReviewDate(new Date());
            review.setReview(reviewDTO.getReview());
            review.setUser(optionalUser.get());
            review.setAd(optionalReservation.get().getAd());

            reviewRepository.save(review);

            Reservation reservation = optionalReservation.get();
            reservation.setReviewStatus(ReviewStatus.TRUE); // Assurez-vous que ReviewStatus est correct
            reservationRepository.save(reservation);

            return true;
        }

        return false;
    }


}
