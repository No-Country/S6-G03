package com.nocountry.mapper;

import com.nocountry.dto.request.Client.ClientRequest;
import com.nocountry.dto.request.Client.ClientRequestModify;
import com.nocountry.dto.request.Client.ClientRequestPassword;
import com.nocountry.dto.response.ClientResponse;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.model.Client;
import com.nocountry.repository.IClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final BCryptPasswordEncoder encryptPassword;
    private final IClientRepository repository;
    private final ImageMapper imageMapper;

    public Client convertToEntity(Client entity, ClientRequest request) throws EmailAlreadyExistException, ClientException {
        if (repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(request.getEmail()));
        }
        validateRequestCreate(request);
        entity.setEmail(request.getEmail());
        if (request.getPassword() != null && request.getConfirmPassword() != null
                && request.getConfirmPassword().equals(request.getPassword())) {
            String encryptedPassword = encryptPassword.encode(request.getPassword());
            entity.setPassword(encryptedPassword);
        } else {
            throw new ClientException(EExceptionMessage.PASSWORD_DO_NOT_MATCH.getMessage());
        }
        return entity;
    }

    public Client convertToEntityModify(Client entity, ClientRequestModify request) throws EmailAlreadyExistException, ClientException {
        String requestEmail = request.getEmail();
        String entityEmail = entity.getEmail();
        boolean existMail = repository.existsByEmail(requestEmail);
        if (existMail && requestEmail.equals(entityEmail)) {
            extractedForConvertToEntityModifyBasic(entity, request);
        } else if (existMail) {
            throw new EmailAlreadyExistException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage());
        } else {
            extractedForConvertToEntityModifyFull(entity, request);
        }
        return entity;
    }

    private static void extractedForConvertToEntityModifyBasic(Client entity, ClientRequestModify request) throws ClientException {
        validateRequestModifyBasic(request);
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setUpdateDate(new Date());
    }

    private static void extractedForConvertToEntityModifyFull(Client entity, ClientRequestModify request) throws ClientException {
        validateRequestModifyFull(request);
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail());
        entity.setAddress(request.getAddress());
        entity.setPhone(request.getPhone());
        entity.setUpdateDate(new Date());
    }

    public Client convertToEntityModifyPassword(Client entity, ClientRequestPassword request) throws ClientException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordChecker = encoder.matches(request.getOldPassword(), entity.getPassword());

        if (passwordChecker) {
            if (request.getNewPassword() != null) {
                if (request.getConfirmNewPassword() != null && request.getConfirmNewPassword().equals(request.getNewPassword())) {
                    entity.setPassword(encryptPassword.encode(request.getNewPassword()));
                } else {
                    throw new ClientException(EExceptionMessage.PASSWORD_DO_NOT_MATCH.getMessage());
                }
            }
        } else {
            throw new ClientException(EExceptionMessage.WRONG_PASSWORD.getMessage());
        }
        return entity;
    }

    public ClientResponse convertToResponse(Client entity) {
        ClientResponse response = new ClientResponse();
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

    public List<ClientResponse> convertToResponseList(List<Client> clientList) {
        List<ClientResponse> clientResponseList = new ArrayList<>();
        for (Client entity : clientList) {
            clientResponseList.add(this.convertToResponse(entity));
        }
        return clientResponseList;
    }

    private static void validateRequestCreate(ClientRequest request) throws ClientException {
        if (request.getEmail() == null || request.getPassword() == null || request.getConfirmPassword() == null) {
            throw new ClientException(EExceptionMessage.REQUEST_WRONG_DATA.getMessage());
        }
    }

    private static void validateRequestModifyBasic(ClientRequestModify request) throws ClientException {
        if (request.getFirstName() == null || request.getLastName() == null || request.getAddress() == null || request.getPhone() == null) {
            throw new ClientException(EExceptionMessage.REQUEST_WRONG_DATA.getMessage());
        }
    }

    private static void validateRequestModifyFull(ClientRequestModify request) throws ClientException {
        if (request.getFirstName() == null || request.getLastName() == null || request.getEmail() == null ||
                request.getAddress() == null || request.getPhone() == null) {
            throw new ClientException(EExceptionMessage.REQUEST_WRONG_DATA.getMessage());
        }
    }
}