package com.nocountry.controller.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.controller.IProvisionController;
import com.nocountry.dto.request.ProvisionRequest;
import com.nocountry.dto.request.ProvisionRequestModify;
import com.nocountry.dto.response.ProvisionResponse;
import com.nocountry.dto.response.ProvisionResponseList;
import com.nocountry.exception.ImageException;
import com.nocountry.exception.ProvisionException;
import com.nocountry.service.IProvisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.nocountry.config.ApiConstants.PROVISION_URI;

@RestController
@RequestMapping(PROVISION_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProvisionController implements IProvisionController {

    private final IProvisionService service;

    @Override
    public ResponseEntity<ProvisionResponse> create(ProvisionRequest request) throws ProvisionException {
        ProvisionResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProvisionResponse> modify(String idProvision, ProvisionRequestModify request) throws ProvisionException {
        ProvisionResponse response = service.modify(idProvision, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProvisionResponse> delete(String idProvision) throws ProvisionException {
        service.delete(idProvision);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ProvisionResponse> getById(String idProvision) throws ProvisionException {
        ProvisionResponse response = service.getById(idProvision);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProvisionResponse>> getAll() throws ProvisionException {
        List<ProvisionResponse> responseList = service.getAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProvisionResponseList> getAllxPages(Optional<Integer> page, Optional<Integer> size) throws ProvisionException {
        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);
        ProvisionResponseList responseList = service.getAllxPages(PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProvisionResponse>> getByValue(String value) throws ProvisionException {
        List<ProvisionResponse> responseList = service.getByValue(value);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProvisionResponse>> getProvisionForHigh() throws ProvisionException {
        List<ProvisionResponse> responseList = service.getForHigh();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProvisionResponse> addFileToProvision(String idProvision, MultipartFile image) throws ProvisionException, ImageException {
        service.addFileToService(idProvision, image);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ProvisionResponse> removeFileToProvision(String idProvision, String idImage) throws ImageException, ProvisionException {
        service.removeFileToService(idProvision, idImage);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}