package com.nocountry.service;

import com.nocountry.dto.request.ContractRequest;
import com.nocountry.dto.response.ContractResponse;
import com.nocountry.dto.response.ContractResponseList;
import com.nocountry.exception.AdminException;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.ContractException;
import com.nocountry.exception.ProvisionException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IContractService {

    @Transactional
    ContractResponse save(ContractRequest request) throws ContractException, ProvisionException, ClientException;

    @Transactional
    ContractResponse modify(String idContract, ContractRequest request) throws ContractException, ProvisionException, ClientException;

    @Transactional
    void delete(String idContract) throws ContractException;

    @Transactional(readOnly = true)
    ContractResponse getById(String idContract) throws ContractException;

    @Transactional(readOnly = true)
    List<ContractResponse> getAll() throws ContractException;

    @Transactional(readOnly = true)
    ContractResponseList getAllxPages(PageRequest request) throws ContractException;

    @Transactional(readOnly = true)
    List<ContractResponse> getByValue(String value) throws ContractException;

    @Transactional(readOnly = true)
    List<ContractResponse> getForHigh() throws AdminException;
}