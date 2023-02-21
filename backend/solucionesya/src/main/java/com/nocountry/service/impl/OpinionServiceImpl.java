package com.nocountry.service.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.dto.request.OpinionRequest;
import com.nocountry.dto.response.OpinionResponse;
import com.nocountry.dto.response.OpinionResponseList;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.OpinionException;
import com.nocountry.exception.ProviderException;
import com.nocountry.exception.ProvisionException;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.mapper.OpinionMapper;
import com.nocountry.model.Opinion;
import com.nocountry.model.OpinionList;
import com.nocountry.repository.IOpinionRepository;
import com.nocountry.service.IOpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpinionServiceImpl implements IOpinionService {

    private final IOpinionRepository repository;
    private final OpinionMapper mapper;


    @Override
    public OpinionResponse save(OpinionRequest request) throws OpinionException, ProviderException, ClientException {
        Opinion entity = new Opinion();
        Opinion entityForConvert = mapper.convertToEntity(entity, request);
        Opinion entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    public OpinionResponse modify(String idOpinion, OpinionRequest request) throws OpinionException, ProviderException, ClientException {
        Optional<Opinion> optionalOpinion = repository.findById(idOpinion);
        if (optionalOpinion.isPresent()) {
            Opinion opinion = optionalOpinion.get();
            Opinion opinionForConvert = mapper.convertToEntityModify(opinion, request);
            Opinion opinionForSave = repository.save(opinionForConvert);
            return mapper.convertToResponse(opinionForSave);
        } else {
            throw new OpinionException(EExceptionMessage.OPINION_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void delete(String idOpinion) throws OpinionException {
        Optional<Opinion> optionalOpinion = repository.findById(idOpinion);
        if (optionalOpinion.isPresent()) {
            Opinion opinion = optionalOpinion.get();
            opinion.setSoftDelete(!opinion.isSoftDelete());
            opinion.setUpdateDate(new Date());
            repository.save(opinion);
        } else {
            throw new OpinionException(EExceptionMessage.OPINION_NOT_FOUND.getMessage());
        }
    }

    @Override
    public OpinionResponse getById(String idOpinion) throws OpinionException {
        if (repository.existsById(idOpinion)) {
            Opinion opinion = repository.getReferenceById(idOpinion);
            return mapper.convertToResponse(opinion);
        } else {
            throw new OpinionException(EExceptionMessage.OPINION_NOT_FOUND.getMessage());
        }
    }

    @Override
    public List<OpinionResponse> getAll() throws OpinionException {
        List<Opinion> opinionList = repository.findAll();
        if (!(opinionList.isEmpty())) {
            return mapper.convertToResponseList(opinionList);
        } else {
            throw new OpinionException(EExceptionMessage.ERROR_DISPLAYING_ALL_OPINION.getMessage());
        }
    }

    @Override
    public OpinionResponseList getAllxPages(PageRequest request) throws OpinionException {
        Page<Opinion> opinionPage = repository.searchByHighPage(request);
        if (!(opinionPage.isEmpty())) {
            OpinionList opinionList = new OpinionList(opinionPage.getContent(), request, opinionPage.getTotalElements());
            return getOpinionResponseList(opinionList);
        } else {
            throw new OpinionException(EExceptionMessage.ERROR_DISPLAYING_ALL_OPINION.getMessage());
        }
    }

    private OpinionResponseList getOpinionResponseList(OpinionList opinionList) {
        OpinionResponseList response = new OpinionResponseList();

        List<OpinionResponse> opinionResponseList = mapper.convertToResponseList(opinionList.getContent());
        response.setContent(opinionResponseList);

        final int nextPage = opinionList.getPageable().next().getPageNumber();
        response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

        final int previousPage = opinionList.getPageable().previousOrFirst().getPageNumber();
        response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

        response.setTotalPages(opinionList.getTotalPages());
        response.setTotalElements(opinionList.getTotalElements());
        return response;
    }

    @Override
    public List<OpinionResponse> getByValue(String value) throws OpinionException {
        if (value == null) {
            value = "";
        }
        List<Opinion> opinionList = repository.searchByValue("%" + value + "%");
        if (!(opinionList.isEmpty())) {
            return mapper.convertToResponseList(opinionList);
        } else {
            throw new OpinionException(EExceptionMessage.OPINION_NOT_FOUND.getMessage());
        }
    }

    @Override
    public List<OpinionResponse> getForHigh() throws OpinionException {
        List<Opinion> opinionList = repository.searchByHigh();
        if (opinionList != null) return mapper.convertToResponseList(opinionList);
        else {
            throw new OpinionException(EExceptionMessage.ERROR_DISPLAYING_OPINION_ACTIVE.getMessage());
        }
    }
}