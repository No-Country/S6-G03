package com.nocountry.repository;

import com.nocountry.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProviderRepository extends JpaRepository<Provider, String> {

    @Query("SELECT p FROM providers p WHERE p.softDelete = false AND p.isBanned = false")
    List<Provider> findAllProviders();

    Provider findByEmail(String email);

    @Query("SELECT (count(p) > 0) FROM providers p WHERE p.email = :email")
    boolean existByEmail(@Param("email") String email);
}