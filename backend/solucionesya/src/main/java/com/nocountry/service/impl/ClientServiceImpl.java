package com.nocountry.service.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.dto.request.Client.ClientRequest;
import com.nocountry.dto.request.Client.ClientRequestModify;
import com.nocountry.dto.request.Client.ClientRequestPassword;
import com.nocountry.dto.response.ClientResponse;
import com.nocountry.dto.response.ClientResponseList;
import com.nocountry.exception.ClientException;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.ImageException;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.mapper.ClientMapper;
import com.nocountry.model.Client;
import com.nocountry.model.ClientList;
import com.nocountry.model.Image;
import com.nocountry.repository.IClientRepository;
import com.nocountry.repository.IImageRepository;
import com.nocountry.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository repository;
    private final ClientMapper mapper;
    private final IImageRepository imageRepository;
    private final ImageServiceImpl imageService;

    @Override
    public ClientResponse save(ClientRequest request) throws ClientException, EmailAlreadyExistException {
        Client entity = new Client();
        Client entityForConvert = mapper.convertToEntity(entity, request);
        Client entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    public ClientResponse modify(String idClient, ClientRequestModify request) throws ClientException, EmailAlreadyExistException {
        Optional<Client> optionalClient = repository.findById(idClient);
        if (optionalClient.isPresent()) {
            Client entity = optionalClient.get();
            Client entityForConvert = mapper.convertToEntityModify(entity, request);
            Client entityForSave = repository.save(entityForConvert);
            return mapper.convertToResponse(entityForSave);
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void modifyPassword(String id, ClientRequestPassword request) throws ClientException {
        Optional<Client> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            Client entity = optionalClient.get();
            Client entityForConvert = mapper.convertToEntityModifyPassword(entity, request);
            Client entityForSave = repository.save(entityForConvert);
            mapper.convertToResponse(entityForSave);
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void delete(String idClient) throws ClientException {
        Optional<Client> optionalClient = repository.findById(idClient);
        if (optionalClient.isPresent()) {
            Client entity = optionalClient.get();
            entity.setSoftDelete(!entity.isSoftDelete());
            entity.setUpdateDate(new Date());
            repository.save(entity);
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.getMessage());
        }
    }

    @Override
    public ClientResponse getById(String idClient) throws ClientException {
        if (repository.existsById(idClient)) {
            Client entity = repository.getReferenceById(idClient);
            return mapper.convertToResponse(entity);
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.getMessage());
        }
    }

    @Override
    public List<ClientResponse> getAll() throws ClientException {
        List<Client> clientList = repository.findAll();
        if (!(clientList.isEmpty())) {
            return mapper.convertToResponseList(clientList);
        } else {
            throw new ClientException(EExceptionMessage.THE_CLIENTS_LIST_IS_EMPTY.getMessage());
        }
    }

    @Override
    public ClientResponseList getAllxPages(PageRequest request) throws ClientException {
        Page<Client> clientPage = repository.searchByHighPage(request);
        if (!(clientPage.isEmpty())) {
            ClientList clientList = new ClientList(clientPage.getContent(), request, clientPage.getTotalElements());
            return getClientResponseList(clientList);
        } else {
            throw new ClientException(EExceptionMessage.THE_CONTRACT_LIST_IS_EMPTY.getMessage());
        }
    }

    private ClientResponseList getClientResponseList(ClientList clientList) {
        ClientResponseList response = new ClientResponseList();

        List<ClientResponse> clientResponseList = mapper.convertToResponseList(clientList.getContent());
        response.setContent(clientResponseList);

        final int nextPage = clientList.getPageable().next().getPageNumber();
        response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

        final int previousPage = clientList.getPageable().previousOrFirst().getPageNumber();
        response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

        response.setTotalPages(clientList.getTotalPages());
        response.setTotalElements(clientList.getTotalElements());
        return response;
    }

    @Override
    public List<ClientResponse> getByValue(String value) throws ClientException {
        if (value == null) {
            value = "";
        }
        List<Client> clientList = repository.searchByValue("%" + value + "%");
        if (!(clientList.isEmpty())) {
            return mapper.convertToResponseList(clientList);
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.getMessage());
        }
    }

    @Override
    public List<ClientResponse> getForHigh() throws ClientException {
        List<Client> clientList = repository.searchByHigh();
        if (clientList != null) return mapper.convertToResponseList(clientList);
        else {
            throw new ClientException(EExceptionMessage.THE_CONTRACT_LIST_IS_EMPTY.getMessage());
        }
    }

    @Override
    public void addImageToClient(String idClient, MultipartFile multipartFile) throws ClientException, ImageException, IOException {
        Optional<Client> optionalClient = repository.findById(idClient);
        if (optionalClient.isPresent()) {
            Client client = repository.getReferenceById(idClient);
            if (client.getImage() != null) {
                throw new ClientException(EExceptionMessage.THE_CLIENT_ALREADY_CONTAINS_IMAGE.getMessage());
            } else {
                Image image = imageService.saveImage(multipartFile);
                image.setClient(client);
                client.setImage(image);
                repository.save(client);
            }
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void modifyImageToClient(String idClient, MultipartFile multipartFile) throws ImageException, IOException, ClientException {
        Optional<Client> optionalClient = repository.findById(idClient);
        if (optionalClient.isPresent()) {
            Client client = repository.getReferenceById(idClient);
            if (client.getImage() != null) {
                Image image = client.getImage();
                imageService.modifyImage(image.getId(), multipartFile);
                image.setClient(client);
                client.setImage(image);
                client.setUpdateDate(new Date());
                repository.save(client);
            } else {
                throw new ImageException(EExceptionMessage.IMAGE_NOT_FOUND.getMessage());
            }
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void removeImageToClient(String idClient, String idImage) throws ImageException, IOException, ClientException {
        Optional<Client> optionalClient = repository.findById(idClient);
        if (optionalClient.isPresent()) {
            Client client = repository.getReferenceById(idClient);
            Optional<Image> optionalFile = imageRepository.findById(idImage);
            if (optionalFile.isPresent()) {
                Image image = imageRepository.getReferenceById(idImage);
                image.setClient(null);
                client.setImage(null);
                imageService.deleteImage(idImage);
                repository.save(client);
            } else {
                throw new ImageException(EExceptionMessage.IMAGE_NOT_FOUND.getMessage());
            }
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.getMessage());
        }
    }
}