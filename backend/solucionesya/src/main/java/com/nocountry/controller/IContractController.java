package com.nocountry.controller;

import com.nocountry.dto.request.Contract.ContractRequest;
import com.nocountry.dto.response.ContractResponse;
import com.nocountry.dto.response.ContractResponseList;
import com.nocountry.exception.AdminException;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.ContractException;
import com.nocountry.exception.ProvisionException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Tag(name = "CONTRACT CONTROLLER")
@Validated
public interface IContractController {

    @PostMapping(path = "/create-contract", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ContractResponse> create(@Valid @RequestBody ContractRequest request) throws ProvisionException, ContractException, ClientException;

    @PutMapping(path = "/modify-contract/{id-contract}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ContractResponse> modify(@NotNull @PathVariable("id-contract") String idContract,
                                            @Valid @RequestBody ContractRequest request) throws ProvisionException, ContractException, ClientException;

    @DeleteMapping(path = "/delete-contract/{id-contract}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ContractResponse> delete(@NotNull @PathVariable("id-contract") String idContract) throws ContractException;

    @GetMapping(path = "/get-contract-by-id/{id-contract}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ContractResponse> getById(@NotNull @PathVariable("id-contract") String idContract) throws ContractException;

    @GetMapping(path = "/get-all-contract", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ContractResponse>> getAll() throws ContractException;

    @GetMapping(path = "/get-all-contract-for-pages", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ContractResponseList> getAllxPages(@RequestParam Optional<Integer> page,
                                                      @RequestParam Optional<Integer> size) throws ContractException;

    @GetMapping(path = "/get-contract-for-value", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ContractResponse>> getByValue(@RequestParam(required = false) String value) throws ContractException;

    @GetMapping(path = "/get-contract-for-high", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ContractResponse>> getContractForHigh() throws AdminException;
}