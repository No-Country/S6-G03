package com.nocountry.dto.request.Client;

import lombok.Data;

@Data
public class ClientRequestPassword {

    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}