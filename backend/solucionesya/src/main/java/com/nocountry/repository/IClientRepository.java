package com.nocountry.repository;

import com.nocountry.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, String> {
}