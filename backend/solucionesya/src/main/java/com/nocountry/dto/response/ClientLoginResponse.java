package com.nocountry.dto.response;

import com.nocountry.model.GeoCode;
import lombok.Data;

import java.util.List;

@Data
public class ClientLoginResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private ImageResponse image;
    private String pathImage;
//    private List<ContractResponse> contractList;
    private List<OpinionResponse> opinionList;
    private String creationDate;
    private String updateDate;
    private GeoCode geoCode;
    private boolean isBanned;
    private boolean softDelete;
    private String token;
}
