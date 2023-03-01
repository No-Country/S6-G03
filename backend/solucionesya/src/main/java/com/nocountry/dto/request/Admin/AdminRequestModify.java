package com.nocountry.dto.request.Admin;

import lombok.Data;

@Data
public class AdminRequestModify {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
}