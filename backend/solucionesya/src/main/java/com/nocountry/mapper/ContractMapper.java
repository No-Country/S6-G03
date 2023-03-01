package com.nocountry.mapper;

import com.nocountry.dto.request.Contract.ContractRequest;
import com.nocountry.dto.response.ContractResponse;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.ContractException;
import com.nocountry.exception.ProvisionException;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.model.Client;
import com.nocountry.model.Contract;
import com.nocountry.model.Provision;
import com.nocountry.repository.IClientRepository;
import com.nocountry.repository.IProvisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContractMapper {

    private final IProvisionRepository provisionRepository;
    private final IClientRepository clientRepository;


    public Contract convertToEntity(Contract entity, ContractRequest request) throws ContractException, ProvisionException, ClientException {
        validateRequest(request);
        Optional<Provision> optionalProvision = provisionRepository.findById(request.getIdProvision());
        if (optionalProvision.isPresent()) {
            Provision provision = optionalProvision.get();
            entity.setProvision(provision);
        } else {
            throw new ProvisionException(EExceptionMessage.PROVISION_NOT_FOUND.getMessage());
        }
        Optional<Client> optionalClient = clientRepository.findById(request.getIdClient());
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            entity.setClient(client);
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.getMessage());
        }
        entity.setAmount(request.getAmount());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public ContractResponse convertToResponse(Contract entity) {
        ContractResponse response = new ContractResponse();
        response.setId(entity.getId());
        response.setProvision(entity.getProvision().getName());
        response.setClient(entity.getClient().getFullName());
        response.setAmount(entity.getAmount());
        response.setDescription(entity.getDescription());

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

    public List<ContractResponse> convertToResponseList(List<Contract> contractList) {
        List<ContractResponse> contractResponseArrayList = new ArrayList<>();
        for (Contract entity : contractList) {
            contractResponseArrayList.add(this.convertToResponse(entity));
        }
        return contractResponseArrayList;
    }

    private static void validateRequest(ContractRequest request) throws ContractException {
        if (request.getIdProvision() == null || request.getIdClient() == null || request.getAmount() == null || request.getDescription() == null) {
            throw new ContractException(EExceptionMessage.REQUEST_WRONG_DATA.getMessage());
        }
    }
}