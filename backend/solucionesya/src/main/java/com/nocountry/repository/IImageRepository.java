package com.nocountry.repository;

import com.nocountry.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepository extends JpaRepository<Image, String> {
}