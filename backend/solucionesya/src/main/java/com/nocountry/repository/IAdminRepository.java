package com.nocountry.repository;

import com.nocountry.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, String> {
}