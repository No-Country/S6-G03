package com.nocountry.repository;

import com.nocountry.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServiceRepository extends JpaRepository<Service, String> {
}