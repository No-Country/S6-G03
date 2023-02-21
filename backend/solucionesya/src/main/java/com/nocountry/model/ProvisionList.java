package com.nocountry.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProvisionList extends PageImpl<Provision> {

    public ProvisionList(List<Provision> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}