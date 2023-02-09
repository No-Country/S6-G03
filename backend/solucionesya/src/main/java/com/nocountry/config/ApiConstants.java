package com.nocountry.config;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.Function;

public interface ApiConstants {

    String ADMIN_URI = "/api/admin";

    String FILE_URI = "/api/file";

    int DEFAULT_PAGE = 0;
    int DEFAULT_PAGE_SIZE = 10;

    Function<Integer, String> uriByPageAsString = page
            -> ServletUriComponentsBuilder.fromCurrentRequest()
            .replaceQueryParam("page", page).toUriString();
}