package com.nocountry.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ProvisionResponse {

    private String id;
    private String name;
    private String category;
    private String description;
    private String price;
    private String paymentLink;
    private ImageResponse image;
    private String pathImage;
    private String provider;
    private List<OpinionResponse> opinionList;
    private String creationDate;
    private String updateDate;
    private boolean softDelete;
}