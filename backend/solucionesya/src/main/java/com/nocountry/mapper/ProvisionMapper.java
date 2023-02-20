package com.nocountry.mapper;

import com.nocountry.dto.request.ProvisionRequest;
import com.nocountry.dto.request.ProvisionRequestModify;
import com.nocountry.dto.response.ProvisionResponse;
import com.nocountry.exception.ProviderException;
import com.nocountry.exception.ProvisionException;
import com.nocountry.list.ECategory;
import com.nocountry.model.Provider;
import com.nocountry.model.Provision;
import com.nocountry.repository.IProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProvisionMapper {

    private static final String REQUEST_WRONG_DATA = "{general.request.wrong.data}";
    private final ImageMapper imageMapper;
    private final IProviderRepository providerRepository;
    private final MessageSource messageSource;

    public Provision convertToEntity(Provision provision, ProvisionRequest request) throws ProvisionException, ProviderException {
        validateRequestCreate(request);
        Optional<Provider> optionalProvider = providerRepository.findById(request.getIdProvider());
        if (optionalProvider.isPresent()) {
            Provider provider = optionalProvider.get();
            provision.setName(request.getName());
            provision.setDescription(request.getDescription());
            ECategory category = ECategory.typeOrValue(request.getCategory());
            provision.setCategory(category);
            provision.setPrice(request.getPrice());
            provision.setProvider(provider);
            return provision;
        } else {
            throw new ProviderException(messageSource.getMessage("provider.not.found", null, Locale.ENGLISH));
        }
    }

    public Provision convertToEntityModify(Provision provision, ProvisionRequestModify request) throws ProvisionException, ProviderException {
        validateRequestModify(request);
        Optional<Provider> optionalProvider = providerRepository.findById(request.getIdProvider());
        if (optionalProvider.isPresent()) {
            Provider provider = optionalProvider.get();
            provision.setName(request.getName());
            provision.setDescription(request.getDescription());
            ECategory category = ECategory.typeOrValue(request.getCategory());
            provision.setCategory(category);
            provision.setPrice(request.getPrice());
            provision.setProvider(provider);
            provision.setUpdateDate(new Date());
            return provision;
        } else {
            throw new ProviderException(messageSource.getMessage("provider.not.found", null, Locale.ENGLISH));
        }
    }

    public ProvisionResponse convertToResponse(Provision provision) {
        ProvisionResponse response = new ProvisionResponse();
        response.setId(provision.getId());
        response.setName(provision.getName());
        response.setDescription(provision.getDescription());
        response.setCategory(provision.getCategory().toString());

        if (response.getImage() != null) {
            response.setImage(imageMapper.convertToResponse(provision.getImage()));
            response.setPathImage(provision.getImage().getPath());
        }

        response.setProvider(provision.getProvider().getFullName());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringCreationDate = sdf.format(provision.getCreationDate());
        String stringUpdateDate;
        if (provision.getUpdateDate() != null) {
            stringUpdateDate = sdf.format(provision.getUpdateDate());
        } else {
            stringUpdateDate = " - ";
        }
        response.setCreationDate(stringCreationDate);
        response.setUpdateDate(stringUpdateDate);
        response.setSoftDelete(provision.isSoftDelete());
        return response;
    }

    public List<ProvisionResponse> convertToResponseList(List<Provision> provisionList) {
        List<ProvisionResponse> provisionResponseList = new ArrayList<>();
        for (Provision provision : provisionList) {
            provisionResponseList.add(this.convertToResponse(provision));
        }
        return provisionResponseList;
    }

    private static void validateRequestCreate(ProvisionRequest request) throws ProvisionException {
        if (request.getName() == null || request.getCategory() == null || request.getDescription() == null ||
                request.getPrice() == null || request.getIdProvider() == null) {
            throw new ProvisionException(REQUEST_WRONG_DATA);
        }
    }

    private static void validateRequestModify(ProvisionRequestModify request) throws ProvisionException {
        if (request.getName() == null || request.getCategory() == null || request.getDescription() == null ||
                request.getPrice() == null || request.getIdProvider() == null) {
            throw new ProvisionException(REQUEST_WRONG_DATA);
        }
    }
}