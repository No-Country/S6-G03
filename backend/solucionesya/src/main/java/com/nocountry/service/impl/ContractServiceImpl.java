package com.nocountry.service.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.dto.request.ContractRequest;
import com.nocountry.dto.response.ContractResponse;
import com.nocountry.dto.response.ContractResponseList;
import com.nocountry.exception.AdminException;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.ContractException;
import com.nocountry.exception.ProvisionException;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.mapper.ContractMapper;
import com.nocountry.model.Contract;
import com.nocountry.model.ContractList;
import com.nocountry.repository.IContractRepository;
import com.nocountry.service.IContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements IContractService {

    private final IContractRepository repository;
    private final ContractMapper mapper;

    @Override
    public ContractResponse save(ContractRequest request) throws ContractException, ProvisionException, ClientException {
        Contract entity = new Contract();
        Contract entityForConvert = mapper.convertToEntity(entity, request);
        Contract entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    public ContractResponse modify(String idContract, ContractRequest request) throws ContractException, ProvisionException, ClientException {
        Optional<Contract> optionalContract = repository.findById(idContract);
        if (optionalContract.isPresent()) {
            Contract entity = optionalContract.get();
            Contract entityForConvert = mapper.convertToEntity(entity, request);
            Contract entityForSave = repository.save(entityForConvert);
            return mapper.convertToResponse(entityForSave);
        } else {
            throw new ContractException(EExceptionMessage.CONTRACT_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void delete(String idContract) throws ContractException {
        Optional<Contract> optionalContract = repository.findById(idContract);
        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            contract.setSoftDelete(!contract.isSoftDelete());
            contract.setUpdateDate(new Date());
            repository.save(contract);
        } else {
            throw new ContractException(EExceptionMessage.CONTRACT_NOT_FOUND.getMessage());
        }
    }

    @Override
    public ContractResponse getById(String idContract) throws ContractException {
        Optional<Contract> optionalContract = repository.findById(idContract);
        if (optionalContract.isPresent()) {
            return mapper.convertToResponse(repository.getReferenceById(idContract));
        } else {
            throw new ContractException(EExceptionMessage.CONTRACT_NOT_FOUND.getMessage());
        }
    }

    @Override
    public List<ContractResponse> getAll() throws ContractException {
        List<Contract> contractList = repository.findAll();
        if (!(contractList.isEmpty())) {
            return mapper.convertToResponseList(contractList);
        } else {
            throw new ContractException(EExceptionMessage.ERROR_DISPLAYING_ALL_CONTRACT.getMessage());
        }
    }

    @Override
    public ContractResponseList getAllxPages(PageRequest request) throws ContractException {
        Page<Contract> contractPage = repository.searchByHighPage(request);
        if (!(contractPage.isEmpty())) {
            ContractList contractList = new ContractList(contractPage.getContent(), request, contractPage.getTotalElements());
            return getContractResponseList(contractList);
        } else {
            throw new ContractException(EExceptionMessage.ERROR_DISPLAYING_ALL_CONTRACT.getMessage());
        }
    }

    private ContractResponseList getContractResponseList(ContractList contractList) {
        ContractResponseList response = new ContractResponseList();

        List<ContractResponse> contractResponseList = mapper.convertToResponseList(contractList.getContent());
        response.setContent(contractResponseList);

        final int nextPage = contractList.getPageable().next().getPageNumber();
        response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

        final int previousPage = contractList.getPageable().previousOrFirst().getPageNumber();
        response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

        response.setTotalPages(contractList.getTotalPages());
        response.setTotalElements(contractList.getTotalElements());
        return response;
    }

    @Override
    public List<ContractResponse> getByValue(String value) throws ContractException {
        if (value == null) {
            value = "";
        }
        List<Contract> contractList = repository.searchByValue("%" + value + "%");
        if (!(contractList.isEmpty())) {
            return mapper.convertToResponseList(contractList);
        } else {
            throw new ContractException(EExceptionMessage.CONTRACT_NOT_FOUND.getMessage());
        }
    }

    @Override
    public List<ContractResponse> getForHigh() throws AdminException {
        List<Contract> contractList = repository.searchByHigh();
        if (contractList != null) return mapper.convertToResponseList(contractList);
        else {
            throw new AdminException(EExceptionMessage.ERROR_DISPLAYING_CONTRACT_ACTIVE.getMessage());
        }
    }
}