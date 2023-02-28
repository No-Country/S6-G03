package com.nocountry.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class OpinionList extends PageImpl<Opinion> {

    public OpinionList(List<Opinion> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}