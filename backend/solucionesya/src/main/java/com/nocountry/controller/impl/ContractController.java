package com.nocountry.controller.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.controller.IContractController;
import com.nocountry.dto.request.Contract.ContractRequest;
import com.nocountry.dto.response.ContractResponse;
import com.nocountry.dto.response.ContractResponseList;
import com.nocountry.exception.AdminException;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.ContractException;
import com.nocountry.exception.ProvisionException;
import com.nocountry.service.IContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.nocountry.config.ApiConstants.CONTRACT_URI;

@RestController
@RequestMapping(CONTRACT_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ContractController implements IContractController {

    private final IContractService service;

    @Override
    public ResponseEntity<ContractResponse> create(ContractRequest request) throws ProvisionException, ContractException, ClientException {
        ContractResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ContractResponse> modify(String idContract, ContractRequest request) throws ProvisionException, ContractException, ClientException {
        ContractResponse response = service.modify(idContract, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContractResponse> delete(String idContract) throws ContractException {
        service.delete(idContract);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContractResponse> getById(String idContract) throws ContractException {
        ContractResponse response = service.getById(idContract);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContractResponse>> getAll() throws ContractException {
        List<ContractResponse> responseList = service.getAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ContractResponseList> getAllxPages(Optional<Integer> page, Optional<Integer> size) throws ContractException {
        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);
        ContractResponseList responseList = service.getAllxPages(PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContractResponse>> getByValue(String value) throws ContractException {
        List<ContractResponse> responseList = service.getByValue(value);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ContractResponse>> getContractForHigh() throws AdminException {
        List<ContractResponse> responseList = service.getForHigh();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}