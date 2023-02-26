package com.nocountry.controller;

import com.nocountry.dto.request.ProviderRequest;
import com.nocountry.dto.request.ProviderRequestModify;
import com.nocountry.dto.request.ProviderRequestPassword;
import com.nocountry.dto.response.ProviderResponse;
import com.nocountry.dto.response.ProviderResponseList;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.ImageException;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name = "PROVIDER CONTROLLER")
@Validated
public interface IProviderController {

    @PostMapping(path = "/create-provider", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProviderResponse> create(@Valid @RequestBody ProviderRequest request) throws ProviderException, EmailAlreadyExistException;

    @PutMapping(path = "/modify-provider/{id-provider}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProviderResponse> modify(@NotNull @PathVariable("id-provider") String idProvider,
                                            @Valid @RequestBody ProviderRequestModify request) throws ProviderException, EmailAlreadyExistException;

    @PutMapping(path = "/modify-provider-password/{id-provider}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProviderResponse> modifyPassword(@NotNull @PathVariable("id-provider") String idProvider,
                                                    @Valid @RequestBody ProviderRequestPassword request) throws ProviderException;

    @DeleteMapping(path = "/delete-provider/{id-provider}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProviderResponse> delete(@NotNull @PathVariable("id-provider") String idProvider) throws ProviderException, ProvisionException;

    @GetMapping(path = "/get-provider-by-id/{id-provider}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProviderResponse> getById(@NotNull @PathVariable("id-provider") String idProvider) throws ProviderException;


    @GetMapping(path = "/get-all-provider", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProviderResponse>> getAll() throws ProviderException;

    @GetMapping(path = "/get-all-provider-for-pages", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProviderResponseList> getAllxPages(@RequestParam Optional<Integer> page,
                                                      @RequestParam Optional<Integer> size) throws ProviderException;

    @GetMapping(path = "/get-provider-for-value", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProviderResponse>> getByValue(@RequestParam(required = false) String value) throws ProviderException;


    @GetMapping(path = "/get-provider-for-high", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProviderResponse>> getProviderForHigh() throws ProviderException;

    @PostMapping(path = "/add-image-to-provider/{id-provider}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProviderResponse> addImageToProvider(@NotNull @PathVariable("id-provider") String idProvider,
                                                       @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) throws ProviderException, ImageException, IOException;

    @PutMapping(path = "/modify-image-to-provider/{id-provider}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProviderResponse> modifyImageToProvider(@NotNull @PathVariable("id-provider") String idProvider,
                                                     @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) throws ProviderException, ImageException, IOException;

    @DeleteMapping(path = "/remove-image-to-provider/{id-provider}/image/{id-image}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProviderResponse> removeImageToProvider(@NotNull @PathVariable("id-provider") String idProvider,
                                                          @NotNull @PathVariable("id-image") String idImage) throws ImageException, ProviderException, IOException;
}