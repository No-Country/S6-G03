package com.nocountry.controller;

import com.nocountry.dto.request.OpinionRequest;
import com.nocountry.dto.response.OpinionResponse;
import com.nocountry.dto.response.OpinionResponseList;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.OpinionException;
import com.nocountry.exception.ProviderException;
import com.nocountry.exception.ProvisionException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Tag(name = "OPINION CONTROLLER")
@Validated
public interface IOpinionController {

    @PostMapping(path = "/create-opinion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OpinionResponse> create(@Valid @RequestBody OpinionRequest request) throws OpinionException, ProvisionException, ClientException, ProviderException;

    @PutMapping(path = "/modify-opinion/{id-opinion}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OpinionResponse> modify(@NotNull @PathVariable("id-opinion") String idOpinion,
                                           @Valid @RequestBody OpinionRequest request) throws OpinionException, ClientException, ProviderException;

    @DeleteMapping(path = "/delete-opinion/{id-opinion}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OpinionResponse> delete(@NotNull @PathVariable("id-opinion") String idOpinion) throws OpinionException;

    @GetMapping(path = "/get-opinion-by-id/{id-opinion}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OpinionResponse> getById(@NotNull @PathVariable("id-opinion") String idOpinion) throws OpinionException;

    @GetMapping(path = "/get-all-opinion", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OpinionResponse>> getAll() throws OpinionException;

    @GetMapping(path = "/get-all-opinion-for-pages", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OpinionResponseList> getAllxPages(@RequestParam Optional<Integer> page,
                                                     @RequestParam Optional<Integer> size) throws OpinionException;

    @GetMapping(path = "/get-opinion-for-value", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OpinionResponse>> getByValue(@RequestParam(required = false) String value) throws OpinionException;

    @GetMapping(path = "/get-opinion-for-high", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<OpinionResponse>> getOpinionForHigh() throws OpinionException;
}