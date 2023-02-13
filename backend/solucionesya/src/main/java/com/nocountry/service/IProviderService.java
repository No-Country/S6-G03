package com.nocountry.service;

import com.nocountry.dto.ProviderRequest;
import com.nocountry.dto.ProviderResponse;
import com.nocountry.exception.UserException;
//import com.nocountry.exception.EmailAlreadyExistException;

import java.util.List;

public interface IProviderService {

    public List<ProviderResponse> getAllProviders() throws UserException;

    public ProviderResponse getById(String id) throws UserException;

    public ProviderResponse createProvider(ProviderRequest providerRequest) throws UserException;

    public ProviderResponse modifyProvider(ProviderRequest providerRequest, String id) throws UserException;

    public void deleteProvider(String id) throws UserException;

}
