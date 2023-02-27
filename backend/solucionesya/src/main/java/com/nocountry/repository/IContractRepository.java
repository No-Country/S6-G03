package com.nocountry.repository;

import com.nocountry.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContractRepository extends JpaRepository<Contract, String> {

    @Query("SELECT c FROM Contract c WHERE c.softDelete = false ORDER BY c.creationDate ASC")
    Page<Contract> searchByHighPage(Pageable page);

    @Query("SELECT c FROM Contract c WHERE c.creationDate LIKE :value AND c.softDelete = false ORDER BY c.creationDate ASC")
    List<Contract> searchByValue(String value);

    @Query("SELECT c FROM Contract c WHERE c.softDelete = false ORDER BY c.creationDate ASC")
    List<Contract> searchByHigh();
}