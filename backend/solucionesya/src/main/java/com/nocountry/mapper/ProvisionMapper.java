package com.nocountry.mapper;

import com.nocountry.dto.request.Provision.ProvisionRequest;
import com.nocountry.dto.request.Provision.ProvisionRequestModify;
import com.nocountry.dto.response.OpinionResponse;
import com.nocountry.dto.response.ProvisionResponse;
import com.nocountry.exception.ProviderException;
import com.nocountry.exception.ProvisionException;
import com.nocountry.list.ECategory;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.model.Opinion;
import com.nocountry.model.Provider;
import com.nocountry.model.Provision;
import com.nocountry.repository.IProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProvisionMapper {

    private final ImageMapper imageMapper;
    private final OpinionMapper opinionMapper;
    private final IProviderRepository providerRepository;

    public Provision convertToEntity(Provision entity, ProvisionRequest request) throws ProvisionException, ProviderException {
        validateRequestCreate(request);
        Optional<Provider> optionalProvider = providerRepository.findById(request.getIdProvider());
        if (optionalProvider.isPresent()) {
            Provider provider = optionalProvider.get();
            entity.setName(request.getName());
            entity.setDescription(request.getDescription());
            ECategory category = ECategory.typeOrValue(request.getCategory());
            entity.setCategory(category);
            entity.setPrice(request.getPrice());
            entity.setPaymentLink(request.getPaymentLink());
            entity.setProvider(provider);
            return entity;
        } else {
            throw new ProviderException(EExceptionMessage.PROVIDER_NOT_FOUND.getMessage());
        }
    }

    public Provision convertToEntityModify(Provision entity, ProvisionRequestModify request) throws ProvisionException, ProviderException {
        validateRequestModify(request);
        Optional<Provider> optionalProvider = providerRepository.findById(request.getIdProvider());
        if (optionalProvider.isPresent()) {
            Provider provider = optionalProvider.get();
            entity.setName(request.getName());
            entity.setDescription(request.getDescription());
            ECategory category = ECategory.typeOrValue(request.getCategory());
            entity.setCategory(category);
            entity.setPrice(request.getPrice());
            entity.setPaymentLink(request.getPaymentLink());
            entity.setProvider(provider);
            entity.setUpdateDate(new Date());
            return entity;
        } else {
            throw new ProviderException(EExceptionMessage.PROVIDER_NOT_FOUND.getMessage());
        }
    }

    public ProvisionResponse convertToResponse(Provision entity) {
        ProvisionResponse response = new ProvisionResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setCategory(entity.getCategory().toString());
        response.setPrice(entity.getPrice());
        response.setPaymentLink(entity.getPaymentLink());

        if (response.getImage() != null) {
            response.setImage(imageMapper.convertToResponse(entity.getImage()));
            response.setPathImage(entity.getImage().getPath());
        }

        response.setProvider(entity.getProvider().getFullName());

        // LIST OF OPINIONS
        List<Opinion> opinionList = entity.getOpinions();
        // ORDER OF THE LIST
        opinionList.sort(((o1, o2) -> CharSequence.compare(o1.getTitle(), o2.getTitle())));
        List<OpinionResponse> opinionResponseList = opinionMapper.convertToResponseList(opinionList);
        response.setOpinionList(opinionResponseList);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringCreationDate = sdf.format(entity.getCreationDate());
        String stringUpdateDate;
        if (entity.getUpdateDate() != null) {
            stringUpdateDate = sdf.format(entity.getUpdateDate());
        } else {
            stringUpdateDate = " - ";
        }

        response.setCreationDate(stringCreationDate);
        response.setUpdateDate(stringUpdateDate);
        response.setSoftDelete(entity.isSoftDelete());
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
            throw new ProvisionException(EExceptionMessage.REQUEST_WRONG_DATA.getMessage());
        }
    }

    private static void validateRequestModify(ProvisionRequestModify request) throws ProvisionException {
        if (request.getName() == null || request.getCategory() == null || request.getDescription() == null ||
                request.getPrice() == null || request.getIdProvider() == null) {
            throw new ProvisionException(EExceptionMessage.REQUEST_WRONG_DATA.getMessage());
        }
    }
}