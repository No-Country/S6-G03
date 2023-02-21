package com.nocountry.mapper;

import com.nocountry.dto.request.OpinionRequest;
import com.nocountry.dto.response.OpinionResponse;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.ProvisionException;
import com.nocountry.model.Client;
import com.nocountry.model.Opinion;
import com.nocountry.model.Provision;
import com.nocountry.repository.IClientRepository;
import com.nocountry.repository.IProvisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OpinionMapper {

    private static final String REQUEST_WRONG_DATA = "{general.request.wrong.data}";
    private final MessageSource messageSource;
    private final IProvisionRepository provisionRepository;
    private final IClientRepository clientRepository;

    public Opinion convertToEntity(Opinion entity, OpinionRequest request) throws ProvisionException, ClientException {
        validateRequest(request);
        Optional<Provision> optionalProvision = provisionRepository.findById(request.getIdProvision());
        if (optionalProvision.isPresent()) {
            Optional<Client> optionalClient = clientRepository.findById(request.getIdClient());
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                Provision provision = optionalProvision.get();
                entity.setTitle(request.getTitle());
                entity.setDescription(request.getDescription());
                entity.setRating(request.getRating());
                entity.setProvision(provision);
                entity.setClient(client);
                return entity;
            } else {
                throw new ClientException(messageSource.getMessage("client.not.found", null, Locale.ENGLISH));
            }
        } else {
            throw new ProvisionException(messageSource.getMessage("provision.not.found", null, Locale.ENGLISH));
        }
    }

    public Opinion convertToEntityModify(Opinion entity, OpinionRequest request) throws ProvisionException, ClientException {
        validateRequest(request);
        Optional<Provision> optionalProvision = provisionRepository.findById(request.getIdProvision());
        if (optionalProvision.isPresent()) {
            Optional<Client> optionalClient = clientRepository.findById(request.getIdClient());
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                Provision provision = optionalProvision.get();
                entity.setTitle(request.getTitle());
                entity.setDescription(request.getDescription());
                entity.setRating(request.getRating());
                entity.setProvision(provision);
                entity.setClient(client);
                entity.setUpdateDate(new Date());
                return entity;
            } else {
                throw new ClientException(messageSource.getMessage("client.not.found", null, Locale.ENGLISH));
            }
        } else {
            throw new ProvisionException(messageSource.getMessage("provision.not.found", null, Locale.ENGLISH));
        }
    }


    public OpinionResponse convertToResponse(Opinion entity) {
        OpinionResponse response = new OpinionResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setRating(entity.getRating());
        response.setProvision(entity.getProvision().getName());
        response.setClient(entity.getClient().getFullName());

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
        response.setSoftDelete(entity.isSoftDelete());
        return response;
    }

    public List<OpinionResponse> convertToResponseList(List<Opinion> opinionList) {
        List<OpinionResponse> opinionResponseList = new ArrayList<>();
        for (Opinion entity : opinionList) {
            opinionResponseList.add(this.convertToResponse(entity));
        }
        return opinionResponseList;
    }

    private static void validateRequest(OpinionRequest request) throws ProvisionException {
        if (request.getTitle() == null || request.getDescription() == null || request.getRating() == null ||
                request.getIdProvision() == null || request.getIdClient() == null) {
            throw new ProvisionException(REQUEST_WRONG_DATA);
        }
    }
}