package com.nocountry.controller.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.controller.IProviderController;
import com.nocountry.dto.request.Provider.ProviderRequest;
import com.nocountry.dto.request.Provider.ProviderRequestModify;
import com.nocountry.dto.request.Provider.ProviderRequestPassword;
import com.nocountry.dto.response.ProviderResponse;
import com.nocountry.dto.response.ProviderResponseList;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.ImageException;
import com.nocountry.exception.ProviderException;
import com.nocountry.exception.ProvisionException;
import com.nocountry.service.IProviderService;
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

import static com.nocountry.config.ApiConstants.PROVIDER_URI;

@RestController
@RequestMapping(PROVIDER_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProviderController implements IProviderController {

    private final IProviderService service;

    @Override
    public ResponseEntity<ProviderResponse> create(ProviderRequest request) throws ProviderException, EmailAlreadyExistException {
        ProviderResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProviderResponse> modify(String idProvider, ProviderRequestModify request) throws ProviderException, EmailAlreadyExistException {
        ProviderResponse response = service.modify(idProvider, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProviderResponse> modifyPassword(String idProvider, ProviderRequestPassword request) throws ProviderException {
        service.modifyPassword(idProvider, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProviderResponse> delete(String idProvider) throws ProviderException, ProvisionException {
        service.delete(idProvider);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ProviderResponse> getById(String idProvider) throws ProviderException {
        ProviderResponse response = service.getById(idProvider);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProviderResponse>> getAll() throws ProviderException {
        List<ProviderResponse> responseList = service.getAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProviderResponseList> getAllxPages(Optional<Integer> page, Optional<Integer> size) throws ProviderException {
        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);
        ProviderResponseList responseList = service.getAllxPages(PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProviderResponse>> getByValue(String value) throws ProviderException {
        List<ProviderResponse> responseList = service.getByValue(value);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProviderResponse>> getProviderForHigh() throws ProviderException {
        List<ProviderResponse> responseList = service.getForHigh();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProviderResponse> addImageToProvider(String idProvider, MultipartFile multipartFile) throws ProviderException, ImageException, IOException {
        service.addImageToProvider(idProvider, multipartFile);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ProviderResponse> modifyImageToProvider(String idProvider, MultipartFile multipartFile) throws ProviderException, ImageException, IOException {
        service.modifyImageToProvider(idProvider, multipartFile);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ProviderResponse> removeImageToProvider(String idProvider, String idImage) throws ImageException, ProviderException, IOException {
        service.removeImageToProvider(idProvider, idImage);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}