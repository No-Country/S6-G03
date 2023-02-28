package com.nocountry.dto.request;

import lombok.Data;

@Data
public class AdminRequestPassword {

    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}