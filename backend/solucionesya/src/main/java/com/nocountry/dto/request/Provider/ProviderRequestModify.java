package com.nocountry.dto.request.Provider;

import lombok.Data;

@Data
public class ProviderRequestModify {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
}