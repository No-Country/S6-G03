package com.nocountry.mapper;

import com.nocountry.dto.request.OpinionRequest;
import com.nocountry.dto.response.OpinionResponse;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.OpinionException;
import com.nocountry.exception.ProviderException;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.model.Client;
import com.nocountry.model.Opinion;
import com.nocountry.model.Provider;
import com.nocountry.repository.IClientRepository;
import com.nocountry.repository.IProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OpinionMapper {

    private final IProviderRepository providerRepository;
    private final IClientRepository clientRepository;

    public Opinion convertToEntity(Opinion entity, OpinionRequest request) throws ClientException, ProviderException, OpinionException {
        validateRequest(request);
        Optional<Provider> optionalProvider = providerRepository.findById(request.getIdProvider());
        if (optionalProvider.isPresent()) {
            Optional<Client> optionalClient = clientRepository.findById(request.getIdClient());
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                Provider provider = optionalProvider.get();
                entity.setTitle(request.getTitle());
                entity.setDescription(request.getDescription());
                entity.setRating(request.getRating());
                entity.setProvider(provider);
                entity.setClient(client);
                return entity;
            } else {
                throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.getMessage());
            }
        } else {
            throw new ProviderException(EExceptionMessage.PROVIDER_NOT_FOUND.getMessage());
        }
    }

    public Opinion convertToEntityModify(Opinion entity, OpinionRequest request) throws OpinionException, ClientException, ProviderException {
        validateRequest(request);
        Optional<Provider> optionalProvider = providerRepository.findById(request.getIdProvider());
        if (optionalProvider.isPresent()) {
            Optional<Client> optionalClient = clientRepository.findById(request.getIdClient());
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                Provider provider = optionalProvider.get();
                entity.setTitle(request.getTitle());
                entity.setDescription(request.getDescription());
                entity.setRating(request.getRating());
                entity.setProvider(provider);
                entity.setClient(client);
                entity.setUpdateDate(new Date());
                return entity;
            } else {
                throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.getMessage());
            }
        } else {
            throw new ProviderException(EExceptionMessage.PROVIDER_NOT_FOUND.getMessage());
        }
    }


    public OpinionResponse convertToResponse(Opinion entity) {
        OpinionResponse response = new OpinionResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());

        response.setRating(getRating(entity.getRating())); //? SHOW STARS

        response.setProvider(entity.getProvider().getFullName());
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

    private static void validateRequest(OpinionRequest request) throws OpinionException {
        if (request.getTitle() == null || request.getDescription() == null || request.getRating() == null ||
                request.getIdProvider() == null || request.getIdClient() == null) {
            throw new OpinionException(EExceptionMessage.REQUEST_WRONG_DATA.getMessage());
        }
    }

    //? SHOW STARS
    public static String getRating(int rating) {
        StringBuilder stars = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            if (i <= rating) {
                stars.append("★");
            } else {
                stars.append("☆");
            }
        }
        return stars.toString();
    }
}