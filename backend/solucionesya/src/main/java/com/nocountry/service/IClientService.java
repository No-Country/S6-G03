package com.nocountry.service;

import com.nocountry.dto.request.Client.ClientRequest;
import com.nocountry.dto.request.Client.ClientRequestModify;
import com.nocountry.dto.request.Client.ClientRequestPassword;
import com.nocountry.dto.response.ClientResponse;
import com.nocountry.dto.response.ClientResponseList;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.ImageException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface IClientService {

    @Transactional
    ClientResponse save(ClientRequest request) throws ClientException, EmailAlreadyExistException;

    @Transactional
    ClientResponse modify(String idClient, ClientRequestModify request) throws ClientException, EmailAlreadyExistException;

    @Transactional
    void modifyPassword(String id, ClientRequestPassword request) throws ClientException;

    @Transactional
    void delete(String idClient) throws ClientException;

    @Transactional(readOnly = true)
    ClientResponse getById(String idClient) throws ClientException;

    @Transactional(readOnly = true)
    List<ClientResponse> getAll() throws ClientException;

    @Transactional(readOnly = true)
    ClientResponseList getAllxPages(PageRequest request) throws ClientException;

    @Transactional(readOnly = true)
    List<ClientResponse> getByValue(String value) throws ClientException;

    @Transactional(readOnly = true)
    List<ClientResponse> getForHigh() throws ClientException;

    @Transactional
    void addImageToClient(String idClient, MultipartFile multipartFile) throws ClientException, ImageException, IOException;

    @Transactional
    void modifyImageToClient(String idClient, MultipartFile multipartFile) throws ImageException, IOException, ClientException;

    @Transactional
    void removeImageToClient(String idClient, String idImage) throws ImageException, IOException, ClientException;
}