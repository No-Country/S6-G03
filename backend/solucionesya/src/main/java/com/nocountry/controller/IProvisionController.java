package com.nocountry.controller;

import com.nocountry.dto.request.Provision.ProvisionRequest;
import com.nocountry.dto.request.Provision.ProvisionRequestModify;
import com.nocountry.dto.response.ProvisionResponse;
import com.nocountry.dto.response.ProvisionResponseList;
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

@Tag(name = "PROVISION CONTROLLER")
@Validated
public interface IProvisionController {


    @PostMapping(path = "/create-provision", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProvisionResponse> create(@Valid @RequestBody ProvisionRequest request) throws ProvisionException, ProviderException;

    @PutMapping(path = "/modify-provision/{id-provision}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProvisionResponse> modify(@NotNull @PathVariable("id-provision") String idProvision,
                                             @Valid @RequestBody ProvisionRequestModify request) throws ProvisionException, ProviderException;

    @DeleteMapping(path = "/delete-provision/{id-provision}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProvisionResponse> delete(@NotNull @PathVariable("id-provision") String idProvision) throws ProvisionException;


    @GetMapping(path = "/get-provision-by-id/{id-provision}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProvisionResponse> getById(@NotNull @PathVariable("id-provision") String idProvision) throws ProvisionException;


    @GetMapping(path = "/get-all-provision", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProvisionResponse>> getAll() throws ProvisionException;

    @GetMapping(path = "/get-all-provision-for-pages", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProvisionResponseList> getAllxPages(@RequestParam Optional<Integer> page,
                                                       @RequestParam Optional<Integer> size) throws ProvisionException;

    @GetMapping(path = "/get-provision-for-value", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProvisionResponse>> getByValue(@RequestParam(required = false) String value) throws ProvisionException;

    @GetMapping(path = "/get-provision-for-high", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProvisionResponse>> getProvisionForHigh() throws ProvisionException;

    @PostMapping(path = "/add-image-to-provision/{id-provision}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProvisionResponse> addImageToProvision(@NotNull @PathVariable("id-provision") String idProvision,
                                                          @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) throws ProvisionException, ImageException, IOException;

    @PutMapping(path = "/modify-image-to-provision/{id-provision}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProvisionResponse> modifyImageToProvision(@NotNull @PathVariable("id-provision") String idProvision,
                                                             @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) throws ProvisionException, ImageException, IOException;

    @DeleteMapping(path = "/remove-image-to-provision/{id-provision}/image/{id-image}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProvisionResponse> removeImageToProvision(@NotNull @PathVariable("id-provision") String idProvision,
                                                             @NotNull @PathVariable("id-image") String idImage) throws ImageException, ProvisionException, IOException;
}