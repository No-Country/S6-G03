package com.nocountry.repository;

import com.nocountry.model.GeoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeoCodeRepository extends JpaRepository<GeoCode, String> {
}