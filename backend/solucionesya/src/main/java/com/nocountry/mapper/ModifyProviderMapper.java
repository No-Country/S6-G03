package com.nocountry.mapper;

import com.nocountry.dto.ProviderRequest;
import com.nocountry.exception.UserException;
import com.nocountry.model.Provider;
import com.nocountry.repository.IProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ModifyProviderMapper {

    @Autowired
    private IProviderRepository repository;

    public Provider modifyProvider(ProviderRequest providerRequest) throws UserException {
        boolean existEmail = repository.existByEmail(providerRequest.getEmail());
        Provider providerEntity = repository.findByEmail(providerRequest.getEmail());
        if (existEmail) {
            throw new UserException("EMAIL_ALREADY_EXIST");
        } else {
            Date updateDate = new Date();
            providerEntity.setFirstName(providerRequest.getFirstName());
            providerEntity.setLastName(providerRequest.getLastName());
            providerEntity.setEmail(providerRequest.getEmail());
            providerEntity.setAddress(providerRequest.getAddress());
            providerEntity.setPhone(providerRequest.getPhone());
            providerEntity.setProfilePhoto(providerRequest.getProfilePhoto());
            providerEntity.setUpdateDate(updateDate);
            return providerEntity;
        }

    }
}
