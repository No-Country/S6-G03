package com.nocountry.service.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.dto.request.AdminRequest;
import com.nocountry.dto.request.AdminRequestModify;
import com.nocountry.dto.request.AdminRequestPassword;
import com.nocountry.dto.response.AdminResponse;
import com.nocountry.dto.response.AdminResponseList;
import com.nocountry.exception.AdminException;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.ImageException;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.mapper.AdminMapper;
import com.nocountry.model.Admin;
import com.nocountry.model.AdminList;
import com.nocountry.model.Image;
import com.nocountry.repository.IAdminRepository;
import com.nocountry.repository.IImageRepository;
import com.nocountry.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {

    private final IAdminRepository repository;
    private final AdminMapper mapper;
    private final IImageRepository imageRepository;
    private final ImageServiceImpl imageService;

    @Override
    public AdminResponse save(AdminRequest request) throws AdminException, EmailAlreadyExistException {
        Admin entity = new Admin();
        Admin entityForConvert = mapper.convertToEntity(entity, request);
        Admin entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    public AdminResponse modify(String idAdmin, AdminRequestModify request) throws AdminException, EmailAlreadyExistException {
        Optional<Admin> optionalAdmin = repository.findById(idAdmin);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            Admin adminForConvert = mapper.convertToEntityModify(admin, request);
            Admin adminForSave = repository.save(adminForConvert);
            return mapper.convertToResponse(adminForSave);
        } else {
            throw new AdminException(EExceptionMessage.ADMIN_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void modifyPassword(String id, AdminRequestPassword request) throws AdminException {
        Optional<Admin> adminOptional = repository.findById(id);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            Admin entityForConvert = mapper.convertToEntityModifyPassword(admin, request);
            Admin entityForSave = repository.save(entityForConvert);
            mapper.convertToResponse(entityForSave);
        } else {
            throw new AdminException(EExceptionMessage.ADMIN_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void delete(String idAdmin) throws AdminException {
        Optional<Admin> optionalAdmin = repository.findById(idAdmin);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setSoftDelete(!admin.isSoftDelete());
            admin.setUpdateDate(new Date());
            repository.save(admin);
        } else {
            throw new AdminException(EExceptionMessage.ADMIN_NOT_FOUND.getMessage());
        }
    }

    @Override
    public AdminResponse getById(String idAdmin) throws AdminException {
        if (repository.existsById(idAdmin)) {
            Admin admin = repository.getReferenceById(idAdmin);
            return mapper.convertToResponse(admin);
        } else {
            throw new AdminException(EExceptionMessage.ADMIN_NOT_FOUND.getMessage());
        }
    }

    @Override
    public List<AdminResponse> getAll() throws AdminException {
        List<Admin> adminList = repository.findAll();
        if (!(adminList.isEmpty())) {
            return mapper.convertToResponseList(adminList);
        } else {
            throw new AdminException(EExceptionMessage.ERROR_DISPLAYING_ALL_ADMIN.getMessage());
        }
    }

    @Override
    public AdminResponseList getAllxPages(PageRequest request) throws AdminException {
        Page<Admin> adminPage = repository.searchByHighPage(request);
        if (!(adminPage.isEmpty())) {
            AdminList adminList = new AdminList(adminPage.getContent(), request, adminPage.getTotalElements());
            return getAdminResponseList(adminList);
        } else {
            throw new AdminException(EExceptionMessage.ERROR_DISPLAYING_ALL_ADMIN.getMessage());
        }
    }

    private AdminResponseList getAdminResponseList(AdminList adminList) {
        AdminResponseList response = new AdminResponseList();

        List<AdminResponse> adminResponseList = mapper.convertToResponseList(adminList.getContent());
        response.setContent(adminResponseList);

        final int nextPage = adminList.getPageable().next().getPageNumber();
        response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

        final int previousPage = adminList.getPageable().previousOrFirst().getPageNumber();
        response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

        response.setTotalPages(adminList.getTotalPages());
        response.setTotalElements(adminList.getTotalElements());
        return response;
    }

    @Override
    public List<AdminResponse> getByValue(String value) throws AdminException {
        if (value == null) {
            value = "";
        }
        List<Admin> adminList = repository.searchByValue("%" + value + "%");
        if (!(adminList.isEmpty())) {
            return mapper.convertToResponseList(adminList);
        } else {
            throw new AdminException(EExceptionMessage.ADMIN_NOT_FOUND.getMessage());
        }
    }

    @Override
    public List<AdminResponse> getForHigh() throws AdminException {
        List<Admin> adminList = repository.searchByHigh();
        if (adminList != null) return mapper.convertToResponseList(adminList);
        else {
            throw new AdminException(EExceptionMessage.ERROR_DISPLAYING_ADMIN_ACTIVE.getMessage());
        }
    }

    @Override
    public void addImageToAdmin(String idAdmin, MultipartFile multipartFile) throws AdminException, ImageException, IOException {
        Optional<Admin> optionalAdmin = repository.findById(idAdmin);
        if (optionalAdmin.isPresent()) {
            Admin admin = repository.getReferenceById(idAdmin);
            if (admin.getImage() != null) {
                throw new AdminException(EExceptionMessage.THE_ADMIN_ALREADY_CONTAINS_IMAGE.getMessage());
            } else {
                Image image = imageService.saveImage(multipartFile);
                image.setAdmin(admin);
                admin.setImage(image);
                repository.save(admin);
            }
        } else {
            throw new AdminException(EExceptionMessage.ADMIN_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void modifyImageToAdmin(String idAdmin, MultipartFile multipartFile) throws AdminException, ImageException, IOException {
        Optional<Admin> optionalAdmin = repository.findById(idAdmin);
        if (optionalAdmin.isPresent()) {
            Admin admin = repository.getReferenceById(idAdmin);
            if (admin.getImage() != null) {
                Image image = admin.getImage();
                imageService.modifyImage(image.getId(), multipartFile);
                image.setAdmin(admin);
                admin.setImage(image);
                admin.setUpdateDate(new Date());
                repository.save(admin);
            } else {
                throw new ImageException(EExceptionMessage.IMAGE_NOT_FOUND.getMessage());
            }
        } else {
            throw new AdminException(EExceptionMessage.ADMIN_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void removeImageToAdmin(String idAdmin, String idImage) throws ImageException, AdminException, IOException {
        Optional<Admin> optionalAdmin = repository.findById(idAdmin);
        if (optionalAdmin.isPresent()) {
            Admin admin = repository.getReferenceById(idAdmin);
            Optional<Image> optionalFile = imageRepository.findById(idImage);
            if (optionalFile.isPresent()) {
                Image image = imageRepository.getReferenceById(idImage);
                image.setAdmin(null);
                admin.setImage(null);
                imageService.deleteImage(idImage);
                repository.save(admin);
            } else {
                throw new ImageException(EExceptionMessage.IMAGE_NOT_FOUND.getMessage());
            }
        } else {
            throw new AdminException(EExceptionMessage.ADMIN_NOT_FOUND.getMessage());
        }
    }
}