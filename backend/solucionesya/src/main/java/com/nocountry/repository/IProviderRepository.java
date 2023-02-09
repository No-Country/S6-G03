package com.nocountry.repository;

import com.nocountry.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProviderRepository extends JpaRepository<Provider, String> {
}