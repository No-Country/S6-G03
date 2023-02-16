package com.nocountry.dto.response;

import com.nocountry.model.GeoCode;
import lombok.Data;

@Data
public class ProvisionResponse {

    private String id;
    private String name;
    private String category;
    private String description;
    private String price;
    private ImageResponse image;
    private String pathImage;
    //private ProviderResponse providerResponse;
    private String creationDate;
    private String updateDate;
    private boolean softDelete;
}