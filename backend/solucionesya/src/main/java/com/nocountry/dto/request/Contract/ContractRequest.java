package com.nocountry.dto.request.Contract;

import lombok.Data;

@Data
public class ContractRequest {

    private String idProvision;
    private String idClient;
    private String amount;
    private String description;
}
