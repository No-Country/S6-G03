package com.nocountry.dto.request.Opinion;

import lombok.Data;

@Data
public class OpinionRequest {

    private String title;
    private String description;
    private Integer rating;
    private String idProvider;
    private String idClient;
}