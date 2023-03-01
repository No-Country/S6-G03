package com.nocountry.dto.response;

import com.nocountry.list.ERoleName;
import com.nocountry.model.GeoCode;
import lombok.Data;

@Data
public class UserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private ERoleName role;
    private String address;
    private String phone;
    private ImageResponse image;
    private String pathImage;
    private String creationDate;
    private String updateDate;
    private GeoCode geoCode;
    private boolean isBanned;
    private boolean softDelete;

}