package com.nocountry.controller.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.controller.IOpinionController;
import com.nocountry.dto.request.OpinionRequest;
import com.nocountry.dto.response.OpinionResponse;
import com.nocountry.dto.response.OpinionResponseList;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.OpinionException;
import com.nocountry.exception.ProvisionException;
import com.nocountry.service.IOpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.nocountry.config.ApiConstants.OPINION_URI;

@RestController
@RequestMapping(OPINION_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OpinionController implements IOpinionController {

    private final IOpinionService service;

    @Override
    public ResponseEntity<OpinionResponse> create(OpinionRequest request) throws OpinionException, ProvisionException, ClientException {
        OpinionResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OpinionResponse> modify(String idOpinion, OpinionRequest request) throws OpinionException, ProvisionException, ClientException {
        OpinionResponse response = service.modify(idOpinion, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OpinionResponse> delete(String idOpinion) throws OpinionException {
        service.delete(idOpinion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<OpinionResponse> getById(String idOpinion) throws OpinionException {
        OpinionResponse response = service.getById(idOpinion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OpinionResponse>> getAll() throws OpinionException {
        List<OpinionResponse> responseList = service.getAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OpinionResponseList> getAllxPages(Optional<Integer> page, Optional<Integer> size) throws OpinionException {
        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);
        OpinionResponseList responseList = service.getAllxPages(PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OpinionResponse>> getByValue(String value) throws OpinionException {
        List<OpinionResponse> responseList = service.getByValue(value);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OpinionResponse>> getOpinionForHigh() throws OpinionException {
        List<OpinionResponse> responseList = service.getForHigh();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}