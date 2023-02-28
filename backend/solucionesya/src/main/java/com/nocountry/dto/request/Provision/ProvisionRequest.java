package com.nocountry.dto.request.Provision;

import lombok.Data;

@Data
public class ProvisionRequest {

    private String name;
    private String category;
    private String description;
    private String price;
    private String idProvider;
}