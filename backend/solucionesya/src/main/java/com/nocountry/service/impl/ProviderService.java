package com.nocountry.service.impl;

import com.nocountry.dto.ProviderRequest;
import com.nocountry.dto.ProviderResponse;
import com.nocountry.exception.UserException;
import com.nocountry.mapper.ModifyProviderMapper;
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

    private ModifyProviderMapper modifyMapper;

    @Override
    public List<ProviderResponse> getAllProviders() throws UserException {
        List<ProviderResponse> providerResponses = new ArrayList<>();
        List<Provider> providers = providerRepository.findAllProviders();
        if (!(providers.isEmpty())) {
            providers.forEach(provider -> providerResponses.add(providerMapper.entityToResponse(provider)));
            return providerResponses;
        } else {
            throw new UserException("PROVIDERS_NOT_FOUND");
        }

    }

    @Override
    public ProviderResponse getById(String id) throws UserException {
        if (providerRepository.existsById(String.valueOf(id))) {
            Provider provider = providerRepository.getReferenceById(id);
            return providerMapper.entityToResponse(provider);
        } else {
            throw new UserException("PROVIDER_NOT_FOUND");
        }

    }

    @Override
    public ProviderResponse createProvider(ProviderRequest providerRequest) throws UserException {
        Provider provider = providerRepository.findByEmail(providerRequest.getEmail());
        if (providerRequest.getEmail().equalsIgnoreCase(provider.getEmail())) {
            throw new UserException("EMAIL_ALREADY_EXIST");
        } else {
            Provider newProvider = providerMapper.requestToEntity(providerRequest);
            Provider providerForSave = providerRepository.save(newProvider);
            return providerMapper.entityToResponse(providerForSave);
        }
    }

    @Override
    public ProviderResponse modifyProvider(ProviderRequest providerRequest, String id) throws UserException {
        Optional<Provider> optionalProvider = providerRepository.findById(id);
        if (optionalProvider.isPresent()) {
            Provider provider = optionalProvider.get();
            Provider providerForConvert = modifyMapper.modifyProvider(providerRequest);
            Provider providerUpdated = providerRepository.save(providerForConvert);

            return providerMapper.entityToResponse(providerUpdated);
        } else {
            throw new UserException("PROVIDER_NOT_FOUND");
        }
    }

    @Override
    public void deleteProvider(String id) throws UserException {
        Optional<Provider> optionalProvider = providerRepository.findById(id);
        if (optionalProvider.isPresent()) {
            Provider provider = optionalProvider.get();
            provider.setSoftDelete(!provider.isSoftDelete());
            provider.setUpdateDate(new Date());
            providerRepository.save(provider);
        } else {
            throw new UserException("PROVIDER_NOT_FOUND");
        }
    }


}
