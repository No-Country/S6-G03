package com.nocountry.controller;

import com.nocountry.dto.request.Admin.AdminRequest;
import com.nocountry.dto.request.Admin.AdminRequestModify;
import com.nocountry.dto.request.Admin.AdminRequestPassword;
import com.nocountry.dto.response.AdminResponse;
import com.nocountry.dto.response.AdminResponseList;
import com.nocountry.exception.AdminException;
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
public interface IAdminController {


    @PostMapping(path = "/create-admin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AdminResponse> create(@Valid @RequestBody AdminRequest request) throws AdminException, EmailAlreadyExistException;


    @PutMapping(path = "/modify-admin/{id-admin}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AdminResponse> modify(@NotNull @PathVariable("id-admin") String idAdmin,
                                         @Valid @RequestBody AdminRequestModify request) throws AdminException, EmailAlreadyExistException;

    @PutMapping(path = "/modify-admin-password/{id-admin}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AdminResponse> modifyPassword(@NotNull @PathVariable("id-admin") String idAdmin,
                                                 @Valid @RequestBody AdminRequestPassword request) throws AdminException;


    @DeleteMapping(path = "/delete-admin/{id-admin}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AdminResponse> delete(@NotNull @PathVariable("id-admin") String idAdmin) throws AdminException;


    @GetMapping(path = "/get-admin-by-id/{id-admin}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AdminResponse> getById(@NotNull @PathVariable("id-admin") String idAdmin) throws AdminException;


    @GetMapping(path = "/get-all-admin", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AdminResponse>> getAll() throws AdminException;


    @GetMapping(path = "/get-all-admin-for-pages", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AdminResponseList> getAllxPages(@RequestParam Optional<Integer> page,
                                                   @RequestParam Optional<Integer> size) throws AdminException;


    @GetMapping(path = "/get-admin-for-value", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AdminResponse>> getByValue(@RequestParam(required = false) String value) throws AdminException;


    @GetMapping(path = "/get-admin-for-high", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AdminResponse>> getAdminForHigh() throws AdminException;

    @PostMapping(path = "/add-image-to-admin/{id-admin}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AdminResponse> addImageToAdmin(@NotNull @PathVariable("id-admin") String idAdmin,
                                                 @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) throws AdminException, ImageException, IOException;

    @PutMapping(path = "/modify-image-to-admin/{id-admin}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AdminResponse> modifyImageToAdmin(@NotNull @PathVariable("id-admin") String idAdmin,
                                                     @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) throws AdminException, ImageException, IOException;

    @DeleteMapping(path = "/remove-image-to-admin/{id-admin}/image/{id-image}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AdminResponse> removeImageToAdmin(@NotNull @PathVariable("id-admin") String idAdmin,
                                                    @NotNull @PathVariable("id-image") String idImage) throws ImageException, AdminException, IOException;
}