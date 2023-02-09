package com.nocountry.service.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.dto.request.AdminRequest;
import com.nocountry.dto.request.AdminRequestModify;
import com.nocountry.dto.request.AdminRequestPassword;
import com.nocountry.dto.response.AdminResponse;
import com.nocountry.dto.response.AdminResponseList;
import com.nocountry.exception.AdminException;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.FileException;
import com.nocountry.list.EPathUpload;
import com.nocountry.mapper.AdminMapper;
import com.nocountry.model.Admin;
import com.nocountry.model.AdminList;
import com.nocountry.model.File;
import com.nocountry.repository.IAdminRepository;
import com.nocountry.repository.IFileRepository;
import com.nocountry.service.IAdminService;
import com.nocountry.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {

    private static final String ADMIN_NOT_FOUND = "{admin.notFound}";
    private final Path pathFolderUpload = Paths.get(EPathUpload.CREATE_ADMIN_FOLDER.toString());
    private final String pathFileUpload = EPathUpload.PATH_ADMIN_IMAGE.toString();
    private final IAdminRepository repository;
    private final AdminMapper mapper;
    private final IFileService fileService;
    private final IFileRepository fileRepository;

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
            throw new AdminException(ADMIN_NOT_FOUND);
        }
    }

    @Override
    public AdminResponse modifyPassword(String id, AdminRequestPassword request) throws AdminException {
        Optional<Admin> adminOptional = repository.findById(id);
        if (adminOptional.isPresent()) {
            Admin internal = adminOptional.get();
            Admin entityForConvert = mapper.convertToEntityModifyPassword(internal, request);
            Admin entityForSave = repository.save(entityForConvert);
            return mapper.convertToResponse(entityForSave);
        } else {
            throw new AdminException(ADMIN_NOT_FOUND);
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
            throw new AdminException(ADMIN_NOT_FOUND);
        }
    }

    @Override
    public AdminResponse getById(String idAdmin) throws AdminException {
        if (repository.existsById(idAdmin)) {
            Admin admin = repository.getReferenceById(idAdmin);
            return mapper.convertToResponse(admin);
        } else {
            throw new AdminException(ADMIN_NOT_FOUND);
        }
    }

    @Override
    public List<AdminResponse> getAll() throws AdminException {
        List<Admin> adminList = repository.findAll();
        if (!(adminList.isEmpty())) {
            return mapper.convertToResponseList(adminList);
        } else {
            throw new AdminException("{admin.errorDisplaying.allAdmin}");
        }
    }

    @Override
    public AdminResponseList getAllxPages(PageRequest request) throws AdminException {
        Page<Admin> adminPage = repository.searchByHighPage(request);
        if (!(adminPage.isEmpty())) {
            AdminList adminList = new AdminList(adminPage.getContent(), request, adminPage.getTotalElements());
            return getAdminResponseList(adminList);
        } else {
            throw new AdminException("{admin.errorDisplaying.allAdmin}");
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
            throw new AdminException(ADMIN_NOT_FOUND);
        }
    }

    @Override
    public List<AdminResponse> getForHigh() throws AdminException {
        List<Admin> adminList = repository.searchByHigh();
        if (adminList != null) return mapper.convertToResponseList(adminList);
        else {
            throw new AdminException("{admin.errorDisplaying.adminActive}");
        }
    }

    @Override
    public void addFileToAdmin(String idAdmin, MultipartFile multipartFile) throws AdminException, FileException {
        Optional<Admin> optionalAdmin = repository.findById(idAdmin);
        if (optionalAdmin.isPresent()) {
            Admin admin = repository.getReferenceById(idAdmin);
            if (admin.getFile() != null) {
                throw new AdminException("{admin.alreadyContains.image}");
            } else {
                File file = fileService.saveFile(multipartFile, pathFolderUpload, pathFileUpload);
                file.setAdmin(admin);
                admin.setFile(file);
                repository.save(admin);
            }
        } else {
            throw new AdminException(ADMIN_NOT_FOUND);
        }
    }

    @Override
    public void removeFileToAdmin(String idAdmin, String idFile) throws FileException, AdminException {
        Optional<Admin> optionalAdmin = repository.findById(idAdmin);
        if (optionalAdmin.isPresent()) {
            Admin admin = repository.getReferenceById(idAdmin);
            Optional<File> optionalFile = fileRepository.findById(idFile);
            if (optionalFile.isPresent()) {
                File file = fileRepository.getReferenceById(idFile);
                file.setAdmin(null);
                admin.setFile(null);
                fileService.deleteFileById(idFile, pathFolderUpload);
                repository.save(admin);
            } else {
                throw new FileException("{file.notFound}");
            }
        } else {
            throw new AdminException(ADMIN_NOT_FOUND);
        }
    }
}
