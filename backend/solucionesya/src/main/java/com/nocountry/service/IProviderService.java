package com.nocountry.service;

import com.nocountry.dto.request.Provider.ProviderRequest;
import com.nocountry.dto.request.Provider.ProviderRequestModify;
import com.nocountry.dto.request.Provider.ProviderRequestPassword;
import com.nocountry.dto.response.ProviderResponse;
import com.nocountry.dto.response.ProviderResponseList;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.ImageException;
import com.nocountry.exception.ProviderException;
import com.nocountry.exception.ProvisionException;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProviderService {

    ProviderResponse save(ProviderRequest request) throws ProviderException, EmailAlreadyExistException;

    ProviderResponse modify(String idProvider, ProviderRequestModify request) throws ProviderException, EmailAlreadyExistException;

    void modifyPassword(String idProvider, ProviderRequestPassword request) throws ProviderException;

    void delete(String idProvider) throws ProviderException, ProvisionException;

    ProviderResponse getById(String idProvider) throws ProviderException;

    List<ProviderResponse> getAll() throws ProviderException;

    ProviderResponseList getAllxPages(PageRequest request) throws ProviderException;

    List<ProviderResponse> getByValue(String value) throws ProviderException;

    @Transactional(readOnly = true)
    List<ProviderResponse> getForHigh() throws ProviderException;

    void addImageToProvider(String idProvider, MultipartFile image) throws ProviderException, ImageException, IOException;

    @Transactional
    void modifyImageToProvider(String idProvider, MultipartFile image) throws ProviderException, ImageException, IOException;

    void removeImageToProvider(String idProvider, String idImage) throws ImageException, ProviderException, IOException;
}
