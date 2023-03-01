package com.nocountry.repository;

import com.nocountry.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findByEmail(String email);

    Boolean existsByEmail(String email);


}