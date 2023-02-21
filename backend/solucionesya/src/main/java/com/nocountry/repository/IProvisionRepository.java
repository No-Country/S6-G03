package com.nocountry.repository;

import com.nocountry.model.Provision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProvisionRepository extends JpaRepository<Provision, String> {

    @Query("SELECT p FROM Provision p WHERE p.softDelete = false ORDER BY p.name ASC")
    Page<Provision> searchByHighPage(PageRequest request);

    @Query("SELECT p FROM Provision p WHERE p.name LIKE :value AND p.softDelete = false ORDER BY p.name ASC")
    List<Provision> searchByValue(@Param("value") String value);

    @Query("SELECT p FROM Provision p WHERE p.softDelete = false ORDER BY p.name ASC")
    List<Provision> searchByHigh();
}