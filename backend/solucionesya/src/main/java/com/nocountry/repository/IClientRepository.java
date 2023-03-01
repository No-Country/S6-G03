package com.nocountry.repository;

import com.nocountry.model.Client;
import com.nocountry.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findByEmail(String email);

    @Query("SELECT c FROM Client c WHERE c.softDelete = false ORDER BY c.lastName ASC")
    Page<Client> searchByHighPage(Pageable page);

    @Query("SELECT c FROM Client c WHERE c.firstName LIKE :value " +
            "OR c.lastName LIKE :value " +
            "AND c.softDelete = false ORDER BY c.lastName ASC")
    List<Client> searchByValue(String value);

    @Query("SELECT c FROM Client c WHERE c.softDelete = false ORDER BY c.lastName ASC")
    List<Client> searchByHigh();

    @Query("SELECT (count(c) > 0) FROM Client c WHERE c.email = :email")
    boolean existsByEmail(@Param("email") String email);
}
