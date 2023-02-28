package com.nocountry.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ContractList extends PageImpl<Contract> {

    public ContractList(List<Contract> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}