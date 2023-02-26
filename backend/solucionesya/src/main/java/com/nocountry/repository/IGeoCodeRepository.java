package com.nocountry.repository;

import com.nocountry.model.GeoCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGeoCodeRepository extends JpaRepository<GeoCode, String> {
}