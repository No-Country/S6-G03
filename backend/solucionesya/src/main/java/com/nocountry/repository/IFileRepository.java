package com.nocountry.repository;

import com.nocountry.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileRepository extends JpaRepository<File, String> {
}