package com.nocountry.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProvisionResponseList {

    private List<ProvisionResponse> content = null;
    @JsonProperty("next_uri")
    private String nextUri;
    @JsonProperty("previous_uri")
    private String previousUri;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("total_elements")
    private Long totalElements;
}