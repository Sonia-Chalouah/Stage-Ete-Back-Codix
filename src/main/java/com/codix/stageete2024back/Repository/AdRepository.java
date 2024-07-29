package com.codix.stageete2024back.Repository;

import com.codix.stageete2024back.Entity.Ad;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
}
