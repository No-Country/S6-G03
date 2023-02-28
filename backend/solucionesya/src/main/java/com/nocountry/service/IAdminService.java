package com.nocountry.service;

import com.nocountry.dto.request.AdminRequest;
import com.nocountry.dto.request.AdminRequestModify;
import com.nocountry.dto.request.AdminRequestPassword;
import com.nocountry.dto.response.AdminResponse;
import com.nocountry.dto.response.AdminResponseList;
import com.nocountry.exception.AdminException;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.ImageException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface IAdminService {

    @Transactional
    AdminResponse save(AdminRequest request) throws AdminException, EmailAlreadyExistException;

    @Transactional
    AdminResponse modify(String idAdmin, AdminRequestModify request) throws AdminException, EmailAlreadyExistException;

    @Transactional
    void modifyPassword(String id, AdminRequestPassword request) throws AdminException;

    @Transactional
    void delete(String idAdmin) throws AdminException;

    @Transactional(readOnly = true)
    AdminResponse getById(String idAdmin) throws AdminException;

    @Transactional(readOnly = true)
    List<AdminResponse> getAll() throws AdminException;

    @Transactional(readOnly = true)
    AdminResponseList getAllxPages(PageRequest request) throws AdminException;

    @Transactional(readOnly = true)
    List<AdminResponse> getByValue(String value) throws AdminException;

    @Transactional(readOnly = true)
    List<AdminResponse> getForHigh() throws AdminException;

    @Transactional
    void addImageToAdmin(String idAdmin, MultipartFile image) throws AdminException, ImageException, IOException;

    @Transactional
    void modifyImageToAdmin(String idAdmin, MultipartFile image) throws AdminException, ImageException, IOException;

    @Transactional
    void removeImageToAdmin(String idAdmin, String idImage) throws ImageException, AdminException, IOException;
}