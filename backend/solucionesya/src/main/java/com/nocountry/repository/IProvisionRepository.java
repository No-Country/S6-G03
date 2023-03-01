package com.nocountry.repository;

import com.nocountry.model.Provision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProvisionRepository extends JpaRepository<Provision, String> {

    @Query("SELECT p FROM Provision p WHERE p.softDelete = false ORDER BY p.name ASC")
    Page<Provision> searchByHighPage(Pageable page);

    @Query("SELECT p FROM Provision p WHERE p.name LIKE :value AND p.softDelete = false ORDER BY p.name ASC")
    List<Provision> searchByValue(@Param("value") String value);

    @Query("SELECT p FROM Provision p WHERE p.softDelete = false ORDER BY p.name ASC")
    List<Provision> searchByHigh();
}