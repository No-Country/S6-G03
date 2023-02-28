package com.nocountry.dto.request.Client;

import lombok.Data;

@Data
public class ClientRequest {

    private String email;
    private String password;
    private String confirmPassword;
}