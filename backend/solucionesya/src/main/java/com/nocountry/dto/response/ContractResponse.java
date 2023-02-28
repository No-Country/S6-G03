package com.nocountry.dto.response;

import lombok.Data;

@Data
public class ContractResponse {

    private String id;
    private String provision;
    private String client;
    private String amount;
    private String description;
    private String creationDate;
    private String updateDate;
    private boolean softDelete;
}