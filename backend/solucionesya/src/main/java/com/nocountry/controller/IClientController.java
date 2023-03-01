package com.nocountry.controller;

import com.nocountry.dto.request.Client.ClientRequest;
import com.nocountry.dto.request.Client.ClientRequestModify;
import com.nocountry.dto.request.Client.ClientRequestPassword;
import com.nocountry.dto.response.ClientResponse;
import com.nocountry.dto.response.ClientResponseList;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.ImageException;
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

@Tag(name = "ADMIN CONTROLLER")
@Validated
public interface IClientController {


    @PostMapping(path = "/create-client", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponse> create(@Valid @RequestBody ClientRequest request) throws ClientException, EmailAlreadyExistException;


    @PutMapping(path = "/modify-client/{id-client}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponse> modify(@NotNull @PathVariable("id-client") String idClient,
                                          @Valid @RequestBody ClientRequestModify request) throws ClientException, EmailAlreadyExistException;

    @PutMapping(path = "/modify-client-password/{id-client}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponse> modifyPassword(@NotNull @PathVariable("id-client") String idClient,
                                                  @Valid @RequestBody ClientRequestPassword request) throws ClientException;

    @DeleteMapping(path = "/delete-client/{id-client}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponse> delete(@NotNull @PathVariable("id-client") String idClient) throws ClientException;

    @GetMapping(path = "/get-client-by-id/{id-client}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponse> getById(@NotNull @PathVariable("id-client") String idClient) throws ClientException;

    @GetMapping(path = "/get-all-client", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ClientResponse>> getAll() throws ClientException;

    @GetMapping(path = "/get-all-client-for-pages", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponseList> getAllxPages(@RequestParam Optional<Integer> page,
                                                    @RequestParam Optional<Integer> size) throws ClientException;

    @GetMapping(path = "/get-client-for-value", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ClientResponse>> getByValue(@RequestParam(required = false) String value) throws ClientException;

    @GetMapping(path = "/get-client-for-high", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ClientResponse>> getClientForHigh() throws ClientException;

    @PostMapping(path = "/add-image-to-client/{id-client}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponse> addImageToClient(@NotNull @PathVariable("id-client") String idClient,
                                                    @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) throws ClientException, ImageException, IOException;

    @PutMapping(path = "/modify-image-to-client/{id-client}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponse> modifyImageToClient(@NotNull @PathVariable("id-client") String idClient,
                                                       @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) throws ClientException, ImageException, IOException;

    @DeleteMapping(path = "/remove-image-to-client/{id-client}/image/{id-image}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponse> removeImageToClient(@NotNull @PathVariable("id-client") String idClient,
                                                       @NotNull @PathVariable("id-image") String idImage) throws ImageException, ClientException, IOException;
}