package com.nocountry.dto;


import com.nocountry.model.GeoCode;
import lombok.Data;

@Data
public class ProviderRequest {


    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String confirmPassword;

    private String address;

    private String phone;

    private String profilePhoto;

    private GeoCode geoCode;
}
