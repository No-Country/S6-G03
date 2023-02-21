package com.nocountry.dto.response;

import lombok.Data;

@Data
public class OpinionResponse {

    private String id;
    private String title;
    private String description;
    private Integer rating;
    private String provision;
    private String client;
    private String creationDate;
    private String updateDate;
    private boolean softDelete;
}