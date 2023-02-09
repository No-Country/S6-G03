package com.nocountry.dto.response;

import com.nocountry.model.GeoCode;
import lombok.Data;

@Data
public class AdminResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String profilePhoto;
    private String creationDate;
    private String updateDate;
    private GeoCode geoCode;
    private boolean isBanned;
    private boolean softDelete;
}