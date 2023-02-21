package com.nocountry.dto.request;

import lombok.Data;

@Data
public class ProvisionRequestModify {

    private String name;
    private String category;
    private String description;
    private String price;
}