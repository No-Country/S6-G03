package com.nocountry.dto;

import com.nocountry.model.GeoCode;
import com.nocountry.model.Opinion;
import com.nocountry.model.Service;
import lombok.*;

import java.util.List;


@Data
public class ProviderResponse {

    private String firstName;

    private String lastName;

    private String address;

    private String phone;

    private String profilePhoto;

    private GeoCode geoCode;

    private List<Opinion> opinions;

    private List<Service> services;
}
