package com.nocountry.mapper;

import com.nocountry.dto.ProviderResponse;
import com.nocountry.model.Provider;
import org.mapstruct.Mapper;

@Mapper
public interface ProviderMapper {

    Provider responseToEntity(ProviderResponse providerResponse);

    ProviderResponse entityToResponse(Provider provider);

}
