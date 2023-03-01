package com.nocountry.controller.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.controller.IClientController;
import com.nocountry.dto.request.Client.ClientRequest;
import com.nocountry.dto.request.Client.ClientRequestModify;
import com.nocountry.dto.request.Client.ClientRequestPassword;
import com.nocountry.dto.response.ClientResponse;
import com.nocountry.dto.response.ClientResponseList;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.ImageException;
import com.nocountry.service.IClientService;
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

import static com.nocountry.config.ApiConstants.CLIENT_URI;

@RestController
@RequestMapping(CLIENT_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ClientController implements IClientController {

    private final IClientService service;

    @Override
    public ResponseEntity<ClientResponse> create(ClientRequest request) throws ClientException, EmailAlreadyExistException {
        ClientResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClientResponse> modify(String idClient, ClientRequestModify request) throws ClientException, EmailAlreadyExistException {
        ClientResponse response = service.modify(idClient, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientResponse> modifyPassword(String idClient, ClientRequestPassword request) throws ClientException {
        service.modifyPassword(idClient, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientResponse> delete(String idClient) throws ClientException {
        service.delete(idClient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ClientResponse> getById(String idClient) throws ClientException {
        ClientResponse response = service.getById(idClient);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ClientResponse>> getAll() throws ClientException {
        List<ClientResponse> responseList = service.getAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientResponseList> getAllxPages(Optional<Integer> page, Optional<Integer> size) throws ClientException {
        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);
        ClientResponseList responseList = service.getAllxPages(PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ClientResponse>> getByValue(String value) throws ClientException {
        List<ClientResponse> responseList = service.getByValue(value);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ClientResponse>> getClientForHigh() throws ClientException {
        List<ClientResponse> responseList = service.getForHigh();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClientResponse> addImageToClient(String idClient, MultipartFile multipartFile) throws ClientException, ImageException, IOException {
        service.addImageToClient(idClient, multipartFile);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ClientResponse> modifyImageToClient(String idClient, MultipartFile multipartFile) throws ClientException, ImageException, IOException {
        service.modifyImageToClient(idClient, multipartFile);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ClientResponse> removeImageToClient(String idClient, String idImage) throws ImageException, ClientException, IOException {
        service.removeImageToClient(idClient, idImage);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}