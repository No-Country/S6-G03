package com.nocountry.mapper;

import com.nocountry.dto.ProviderRequest;
import com.nocountry.dto.ProviderResponse;
import com.nocountry.model.Provider;
import org.mapstruct.Mapper;

@Mapper
public interface ProviderMapper {

    Provider requestToEntity(ProviderRequest providerRequest);

    ProviderResponse entityToResponse(Provider provider);





}
