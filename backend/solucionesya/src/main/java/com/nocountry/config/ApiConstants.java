package com.nocountry.config;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.Function;

public interface ApiConstants {

    String ADMIN_URI = "/api/admin";
    String IMAGE_URI = "/api/image";
    String PROVIDER_URI = "/api/provider";
    String PROVISION_URI = "/api/provision";
    String OPINION_URI = "/api/opinion";
    String CONTRACT_URI = "/api/contract";

    int DEFAULT_PAGE = 0;
    int DEFAULT_PAGE_SIZE = 10;

    Function<Integer, String> uriByPageAsString = page
            -> ServletUriComponentsBuilder.fromCurrentRequest()
            .replaceQueryParam("page", page).toUriString();
}