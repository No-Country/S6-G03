package com.nocountry.repository;

import com.nocountry.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, String> {

    @Query("SELECT a FROM Admin a WHERE a.softDelete = false ORDER BY a.lastName ASC")
    Page<Admin> searchByHighPage(Pageable page);

    @Query("SELECT a FROM Admin a WHERE a.firstName LIKE :value " +
            "OR a.lastName LIKE :value " +
            "AND a.softDelete = false ORDER BY a.lastName ASC")
    List<Admin> searchByValue(String value);

    @Query("SELECT a FROM Admin a WHERE a.softDelete = false ORDER BY a.lastName ASC")
    List<Admin> searchByHigh();

    @Query("SELECT (count(a) > 0) FROM Admin a WHERE a.email = :email")
    boolean existsByEmail(@Param("email") String email);
}