package com.codix.stageete2024back.Repository;

import com.codix.stageete2024back.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByCompanyId( Long companyId);
}
