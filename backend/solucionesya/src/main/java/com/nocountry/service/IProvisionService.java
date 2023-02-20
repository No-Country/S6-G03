package com.nocountry.service;

import com.nocountry.dto.request.ProvisionRequest;
import com.nocountry.dto.request.ProvisionRequestModify;
import com.nocountry.dto.response.ProvisionResponse;
import com.nocountry.dto.response.ProvisionResponseList;
import com.nocountry.exception.ImageException;
import com.nocountry.exception.ProvisionException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface IProvisionService {

    @Transactional
    ProvisionResponse save(ProvisionRequest request) throws ProvisionException;

    @Transactional
    ProvisionResponse modify(String idProvision, ProvisionRequestModify request) throws ProvisionException;

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
    void addFileToService(String idProvision, MultipartFile image) throws ProvisionException, ImageException;

    @Transactional
    void removeFileToService(String idProvision, String idImage) throws ImageException, ProvisionException;
}