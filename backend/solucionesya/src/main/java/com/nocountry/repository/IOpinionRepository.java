package com.nocountry.repository;

import com.nocountry.model.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOpinionRepository extends JpaRepository<Opinion, String> {
}