package com.nocountry.service;

import com.nocountry.dto.request.ProvisionRequest;
import com.nocountry.dto.request.ProvisionRequestModify;
import com.nocountry.dto.response.ProvisionResponse;
import com.nocountry.dto.response.ProvisionResponseList;
import com.nocountry.exception.ImageException;
import com.nocountry.exception.ProviderException;
import com.nocountry.exception.ProvisionException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface IProvisionService {

    @Transactional
    ProvisionResponse save(ProvisionRequest request) throws ProvisionException, ProviderException;

    @Transactional
    ProvisionResponse modify(String idProvision, ProvisionRequestModify request) throws ProvisionException, ProviderException;

    @Transactional
    void delete(String idProvision) throws ProvisionException;

    @Transactional(readOnly = true)
    ProvisionResponse getById(String idProvision) throws ProvisionException;

    @Transactional(readOnly = true)
    List<ProvisionResponse> getAll() throws ProvisionException;

    @Transactional(readOnly = true)
    ProvisionResponseList getAllxPages(PageRequest request) throws ProvisionException;

    @Transactional(readOnly = true)
    List<ProvisionResponse> getByValue(String value) throws ProvisionException;

    @Transactional(readOnly = true)
    List<ProvisionResponse> getForHigh() throws ProvisionException;

    @Transactional
    void addImageToProvision(String idProvision, MultipartFile image) throws ProvisionException, ImageException, IOException;

    @Transactional
    void modifyImageToProvision(String idProvision, MultipartFile image) throws ImageException, IOException, ProvisionException;

    @Transactional
    void removeImageToProvision(String idProvision, String idImage) throws ImageException, ProvisionException, IOException;
}