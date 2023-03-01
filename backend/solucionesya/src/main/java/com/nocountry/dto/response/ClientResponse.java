package com.nocountry.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ClientResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private ImageResponse image;
    private String pathImage;
    private List<ProvisionResponse> provisionList;
    private List<OpinionResponse> opinionList;
    private String creationDate;
    private String updateDate;
    private boolean isBanned;
    private boolean softDelete;
}