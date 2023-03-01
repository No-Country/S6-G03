package com.nocountry.mapper;

import com.nocountry.dto.request.ProviderRequest;
import com.nocountry.dto.request.ProviderRequestModify;
import com.nocountry.dto.request.ProviderRequestPassword;
import com.nocountry.dto.response.OpinionResponse;
import com.nocountry.dto.response.ProviderResponse;
import com.nocountry.dto.response.ProvisionResponse;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.ProviderException;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.model.Opinion;
import com.nocountry.model.Provider;
import com.nocountry.model.Provision;
import com.nocountry.repository.IProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProviderMapper {

    private final BCryptPasswordEncoder encryptPassword;
    private final IProviderRepository repository;
    private final ImageMapper imageMapper;
    private final ProvisionMapper provisionMapper;
    private final OpinionMapper opinionMapper;

    public Provider convertToEntity(Provider entity, ProviderRequest request) throws EmailAlreadyExistException, ProviderException {
        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(request.getEmail()));
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
            throw new ProviderException(EExceptionMessage.PASSWORD_DOES_NOT_MATCH.getMessage());
        }
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        return entity;
    }

    public Provider convertToEntityModify(Provider entity, ProviderRequestModify request) throws EmailAlreadyExistException, ProviderException {
        String requestEmail = request.getEmail();
        String entityEmail = entity.getEmail();
        boolean existMail = repository.existsByEmail(requestEmail);

        if (existMail && requestEmail.equals(entityEmail)) {
            extractedForConvertToEntityModifyBasic(entity, request);
        } else if (existMail) {
            throw new EmailAlreadyExistException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(request.getEmail()));
        } else {
            extractedForConvertToEntityModifyFull(entity, request);
        }
        return entity;
    }

    private static void extractedForConvertToEntityModifyBasic(Provider entity, ProviderRequestModify request) throws ProviderException {
        validateRequestModifyBasic(request);
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setUpdateDate(new Date());
    }

    private static void extractedForConvertToEntityModifyFull(Provider entity, ProviderRequestModify request) throws ProviderException {
        validateRequestModifyFull(request);
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setUpdateDate(new Date());
    }

    public ProviderResponse convertToResponse(Provider entity) {
        ProviderResponse response = new ProviderResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setFullName(entity.getFullName());
        response.setEmail(entity.getEmail());
        response.setAddress(entity.getAddress());
        response.setPhone(entity.getPhone());

        if (entity.getImage() != null) {
            response.setImage(imageMapper.convertToResponse(entity.getImage()));
            response.setPathImage(entity.getImage().getPath());
        }

        // LIST OF PROVISIONS
        List<Provision> provisionList = entity.getProvisions();
        // ORDER OF THE LIST
        provisionList.sort((o1, o2) -> CharSequence.compare(o1.getName(), o2.getName()));
        List<ProvisionResponse> provisionResponseList = provisionMapper.convertToResponseList(provisionList);
        response.setProvisionList(provisionResponseList);

        // LIST OF OPINIONS
        List<Opinion> opinionList = entity.getOpinions();
        // ORDER OF THE LIST
        opinionList.sort(((o1, o2) -> CharSequence.compare(o1.getTitle(), o2.getTitle())));
        List<OpinionResponse> opinionResponseList = opinionMapper.convertToResponseList(opinionList);
        response.setOpinionList(opinionResponseList);

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

    public List<ProviderResponse> convertToResponseList(List<Provider> providerList) {
        List<ProviderResponse> providerResponseList = new ArrayList<>();
        for (Provider entity : providerList) {
            providerResponseList.add(this.convertToResponse(entity));
        }
        return providerResponseList;
    }

    public Provider convertToEntityModifyPassword(Provider entity, ProviderRequestPassword request) throws ProviderException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordChecker = encoder.matches(request.getOldPassword(), entity.getPassword());

        if (passwordChecker) {
            if (request.getNewPassword() != null) {
                if (request.getConfirmNewPassword() != null && request.getConfirmNewPassword().equals(request.getNewPassword())) {
                    entity.setPassword(encryptPassword.encode(request.getNewPassword()));
                } else {
                    throw new ProviderException(EExceptionMessage.PASSWORD_DOES_NOT_MATCH.getMessage());
                }
            }
        } else {
            throw new ProviderException(EExceptionMessage.WRONG_PASSWORD.getMessage());
        }
        return entity;
    }

    private static void validateRequestCreate(ProviderRequest request) throws ProviderException {
        if (request.getFirstName() == null || request.getLastName() == null || request.getEmail() == null ||
                request.getPassword() == null || request.getAddress() == null || request.getPhone() == null ||
                request.getConfirmPassword() == null) {
            throw new ProviderException(EExceptionMessage.REQUEST_WRONG_DATA.getMessage());
        }
    }

    private static void validateRequestModifyBasic(ProviderRequestModify request) throws ProviderException {
        if (request.getFirstName() == null || request.getLastName() == null || request.getAddress() == null || request.getPhone() == null) {
            throw new ProviderException(EExceptionMessage.REQUEST_WRONG_DATA.getMessage());
        }
    }

    private static void validateRequestModifyFull(ProviderRequestModify request) throws ProviderException {
        if (request.getFirstName() == null || request.getLastName() == null || request.getEmail() == null ||
                request.getAddress() == null || request.getPhone() == null) {
            throw new ProviderException(EExceptionMessage.REQUEST_WRONG_DATA.getMessage());
        }
    }
}