package com.nocountry.service.impl;

import com.nocountry.dto.ProviderResponse;
import com.nocountry.mapper.ProviderMapper;
import com.nocountry.model.Provider;
import com.nocountry.repository.IProviderRepository;
import com.nocountry.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProviderService implements IProviderService {

    @Autowired
    private ProviderMapper providerMapper;

    @Autowired
    private IProviderRepository providerRepository;

    @Override
    public List<ProviderResponse> getAllProviders() {
        List<ProviderResponse> providerResponses = new ArrayList<>();
        List<Provider> providers = providerRepository.findAllProviders();
        providers.forEach(provider -> providerResponses.add(providerMapper.entityToResponse(provider)));
        return providerResponses;
    }
}
