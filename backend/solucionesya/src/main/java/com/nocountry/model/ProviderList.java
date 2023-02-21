package com.nocountry.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProviderList extends PageImpl<Provider> {

    public ProviderList(List<Provider> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}