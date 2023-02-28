package com.nocountry.dto.request.Provision;

import lombok.Data;

@Data
public class ProvisionRequestModify {

    private String name;
    private String category;
    private String description;
    private String price;
    private String paymentLink;
    private String idProvider;
}