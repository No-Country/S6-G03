package com.nocountry.dto.request.Client;

import lombok.Data;

@Data
public class ClientRequestModify {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
}