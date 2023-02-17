package com.nocountry.mapper;

import com.nocountry.dto.request.AdminRequest;
import com.nocountry.dto.request.AdminRequestModify;
import com.nocountry.dto.request.AdminRequestPassword;
import com.nocountry.dto.response.AdminResponse;
import com.nocountry.exception.AdminException;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.model.Admin;
import com.nocountry.repository.IAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class AdminMapper {

    private static final String REQUEST_WRONG_DATA = "{general.request.wrong.data}";
    private final BCryptPasswordEncoder encryptPassword;
    private final IAdminRepository repository;
    private final ImageMapper imageMapper;
    private final MessageSource messageSource;

    public Admin convertToEntity(Admin entity, AdminRequest request) throws AdminException, EmailAlreadyExistException {
        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException(messageSource.getMessage("general.email.already.exists", null, Locale.ENGLISH));
        }
        validateRequestCreate(request);
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        if (request.getPassword() != null && request.getConfirmPassword() != null
                && request.getConfirmPassword().equals(request.getPassword())) {
            String encryptedPassword = encryptPassword.encode(request.getPassword());
            entity.setPassword(encryptedPassword);
        } else {
            throw new AdminException(messageSource.getMessage("general.passwords.do.not.match", null, Locale.ENGLISH));
        }
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        return entity;
    }

    public Admin convertToEntityModify(Admin entity, AdminRequestModify request)
            throws EmailAlreadyExistException, AdminException {

        String requestEmail = request.getEmail();
        String entityEmail = entity.getEmail();
        boolean existMail = repository.existsByEmail(requestEmail);

        if (existMail && requestEmail.equals(entityEmail)) {
            extractedForConvertToEntityModifyBasic(entity, request);
        } else if (existMail) {
            throw new EmailAlreadyExistException("{general.email.already.exists}");
        } else {
            extractedForConvertToEntityModifyFull(entity, request);
        }
        return entity;
    }

    private static void extractedForConvertToEntityModifyBasic(Admin entity, AdminRequestModify request) throws AdminException {
        validateRequestModifyBasic(request);
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setUpdateDate(new Date());
    }

    private static void extractedForConvertToEntityModifyFull(Admin entity, AdminRequestModify request) throws AdminException {
        validateRequestModifyFull(request);
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setUpdateDate(new Date());
    }

    public AdminResponse convertToResponse(Admin entity) {
        AdminResponse response = new AdminResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setAddress(entity.getAddress());
        response.setPhone(entity.getPhone());

        if (entity.getImage() != null) {
            response.setImage(imageMapper.convertToResponse(entity.getImage()));
            response.setPathImage(entity.getImage().getPath());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringCreationDate = sdf.format(entity.getCreationDate());
        String stringUpdateDate;
        if (entity.getUpdateDate() != null) {
            stringUpdateDate = sdf.format(entity.getUpdateDate());
        } else {
            stringUpdateDate = " - ";
        }
        response.setCreationDate(stringCreationDate);
        response.setUpdateDate(stringUpdateDate);
        response.setBanned(entity.isBanned());
        response.setSoftDelete(entity.isSoftDelete());
        return response;
    }

    public List<AdminResponse> convertToResponseList(List<Admin> adminList) {
        List<AdminResponse> adminResponseList = new ArrayList<>();
        for (Admin entity : adminList) {
            adminResponseList.add(this.convertToResponse(entity));
        }
        return adminResponseList;
    }

    public Admin convertToEntityModifyPassword(Admin entity, AdminRequestPassword request) throws AdminException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordChecker = encoder.matches(request.getOldPassword(), entity.getPassword());

        if (passwordChecker) {
            if (request.getNewPassword() != null) {
                if (request.getConfirmNewPassword() != null && request.getConfirmNewPassword().equals(request.getNewPassword())) {
                    entity.setPassword(encryptPassword.encode(request.getNewPassword()));
                } else {
                    throw new AdminException("{general.passwords.do.not.match}");
                }
            }
        } else {
            throw new AdminException("{general.wrong.password}");
        }
        return entity;
    }

    private static void validateRequestCreate(AdminRequest request) throws AdminException {
        if (request.getFirstName() == null || request.getLastName() == null || request.getEmail() == null ||
                request.getPassword() == null || request.getAddress() == null || request.getPhone() == null ||
                request.getConfirmPassword() == null) {
            throw new AdminException(REQUEST_WRONG_DATA);
        }
    }

    private static void validateRequestModifyBasic(AdminRequestModify request) throws AdminException {
        if (request.getFirstName() == null || request.getLastName() == null || request.getAddress() == null || request.getPhone() == null) {
            throw new AdminException(REQUEST_WRONG_DATA);
        }
    }

    private static void validateRequestModifyFull(AdminRequestModify request) throws AdminException {
        if (request.getFirstName() == null || request.getLastName() == null || request.getEmail() == null ||
                request.getAddress() == null || request.getPhone() == null) {
            throw new AdminException(REQUEST_WRONG_DATA);
        }
    }
}