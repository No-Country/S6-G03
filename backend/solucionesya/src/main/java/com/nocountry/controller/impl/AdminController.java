package com.nocountry.controller.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.controller.IAdminController;
import com.nocountry.dto.request.AdminRequest;
import com.nocountry.dto.request.AdminRequestModify;
import com.nocountry.dto.request.AdminRequestPassword;
import com.nocountry.dto.response.AdminResponse;
import com.nocountry.dto.response.AdminResponseList;
import com.nocountry.exception.AdminException;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.ImageException;
import com.nocountry.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.nocountry.config.ApiConstants.ADMIN_URI;

@RestController
@RequestMapping(ADMIN_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminController implements IAdminController {

    private final IAdminService service;

    @Override
    public ResponseEntity<AdminResponse> create(AdminRequest request) throws AdminException, EmailAlreadyExistException {
        AdminResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AdminResponse> modify(String idAdmin, AdminRequestModify request) throws AdminException, EmailAlreadyExistException {
        AdminResponse response = service.modify(idAdmin, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AdminResponse> modifyPassword(String idAdmin, AdminRequestPassword request) throws AdminException {
        service.modifyPassword(idAdmin, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AdminResponse> delete(String idAdmin) throws AdminException {
        service.delete(idAdmin);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<AdminResponse> getById(String idAdmin) throws AdminException {
        AdminResponse response = service.getById(idAdmin);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AdminResponse>> getAll() throws AdminException {
        List<AdminResponse> responseList = service.getAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AdminResponseList> getAllxPages(Optional<Integer> page, Optional<Integer> size) throws AdminException {
        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);
        AdminResponseList responseList = service.getAllxPages(PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AdminResponse>> getByValue(String value) throws AdminException {
        List<AdminResponse> responseList = service.getByValue(value);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AdminResponse>> getAdminForHigh() throws AdminException {
        List<AdminResponse> responseList = service.getForHigh();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AdminResponse> addImageToAdmin(String idAdmin, MultipartFile multipartFile) throws AdminException, ImageException, IOException {
        service.addImageToAdmin(idAdmin, multipartFile);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<AdminResponse> modifyImageToAdmin(String idAdmin, MultipartFile multipartFile) throws AdminException, ImageException, IOException {
        service.modifyImageToAdmin(idAdmin, multipartFile);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<AdminResponse> removeImageToAdmin(String idAdmin, String idImage) throws ImageException, AdminException, IOException {
        service.removeImageToAdmin(idAdmin, idImage);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}