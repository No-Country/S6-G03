package com.nocountry.repository;

import com.nocountry.model.Opinion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOpinionRepository extends JpaRepository<Opinion, String> {

    @Query("SELECT o FROM Opinion o WHERE o.softDelete = false ORDER BY o.title ASC")
    Page<Opinion> searchByHighPage(Pageable page);

    @Query("SELECT o FROM Opinion o WHERE o.title LIKE :value " +
            "OR o.description LIKE :value " +
            "AND o.softDelete = false ORDER BY o.title ASC")
    List<Opinion> searchByValue(String value);

    @Query("SELECT o FROM Opinion o WHERE o.softDelete = false ORDER BY o.title ASC")
    List<Opinion> searchByHigh();
}