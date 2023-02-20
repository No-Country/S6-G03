package com.nocountry.repository;

import com.nocountry.model.Provider;
import com.nocountry.model.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProviderRepository extends JpaRepository<Provider, String> {

    @Query("SELECT p FROM Provider p WHERE p.softDelete = false ORDER BY p.lastName ASC")
    Page<Provider> searchByHighPage(PageRequest request);

    @Query("SELECT p FROM Provider p WHERE p.firstName LIKE :value " +
            "OR p.lastName LIKE :value " +
            "AND p.softDelete = false ORDER BY p.lastName ASC")
    List<Provider> searchByValue(String value);

    @Query("SELECT p FROM Provider p WHERE p.softDelete = false ORDER BY p.lastName ASC")
    List<Provider> searchByHigh();

    @Query("SELECT (count(p) > 0) FROM Provider p WHERE p.email = :email")
    boolean existsByEmail(@Param("email") String email);
}