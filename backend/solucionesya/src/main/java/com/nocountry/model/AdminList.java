package com.nocountry.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AdminList extends PageImpl<Admin> {

    public AdminList(List<Admin> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}