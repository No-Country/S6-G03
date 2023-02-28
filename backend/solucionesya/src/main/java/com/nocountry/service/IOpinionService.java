package com.nocountry.service;

import com.nocountry.dto.request.Opinion.OpinionRequest;
import com.nocountry.dto.response.OpinionResponse;
import com.nocountry.dto.response.OpinionResponseList;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.OpinionException;
import com.nocountry.exception.ProviderException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IOpinionService {

    @Transactional
    OpinionResponse save(OpinionRequest request) throws OpinionException, ProviderException, ClientException;

    @Transactional
    OpinionResponse modify(String idOpinion, OpinionRequest request) throws OpinionException, ProviderException, ClientException;

    @Transactional
    void delete(String idOpinion) throws OpinionException;

    @Transactional(readOnly = true)
    OpinionResponse getById(String idOpinion) throws OpinionException;

    @Transactional(readOnly = true)
    List<OpinionResponse> getAll() throws OpinionException;

    @Transactional(readOnly = true)
    OpinionResponseList getAllxPages(PageRequest request) throws OpinionException;

    @Transactional(readOnly = true)
    List<OpinionResponse> getByValue(String value) throws OpinionException;

    @Transactional(readOnly = true)
    List<OpinionResponse> getForHigh() throws OpinionException;
}