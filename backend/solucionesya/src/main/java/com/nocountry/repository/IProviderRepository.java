package com.nocountry.repository;

import com.nocountry.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProviderRepository extends JpaRepository<Provider, String> {

    @Query("SELECT p FROM providers p WHERE p.softDelete = false AND p.isBanned = false")
    List<Provider> findAllProviders();

}