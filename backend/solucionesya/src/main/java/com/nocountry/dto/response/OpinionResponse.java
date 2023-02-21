package com.nocountry.dto.response;

import lombok.Data;

@Data
public class OpinionResponse {

    private String id;
    private String title;
    private String description;
    private String rating;
    private String provider;
    private String client;
    private String creationDate;
    private String updateDate;
    private boolean softDelete;
}