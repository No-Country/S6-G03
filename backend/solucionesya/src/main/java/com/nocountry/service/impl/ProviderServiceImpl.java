package com.nocountry.service.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.dto.request.ProviderRequest;
import com.nocountry.dto.request.ProviderRequestModify;
import com.nocountry.dto.request.ProviderRequestPassword;
import com.nocountry.dto.response.ProviderResponse;
import com.nocountry.dto.response.ProviderResponseList;
import com.nocountry.exception.EmailAlreadyExistException;
import com.nocountry.exception.ImageException;
import com.nocountry.exception.ProviderException;
import com.nocountry.list.EPathUpload;
import com.nocountry.mapper.ProviderMapper;
import com.nocountry.model.Image;
import com.nocountry.model.Provider;
import com.nocountry.model.ProviderList;
import com.nocountry.repository.IImageRepository;
import com.nocountry.repository.IProviderRepository;
import com.nocountry.service.IImageService;
import com.nocountry.service.IProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements IProviderService {

    private static final String PROVIDER_NOT_FOUND = "{provider.not.found}";
    private final Path pathFolderUpload = Paths.get(EPathUpload.CREATE_PROVIDER_FOLDER.toString());
    private final String pathFileUpload = EPathUpload.PATH_PROVIDER_IMAGE.toString();
    private final ProviderMapper mapper;
    private final IProviderRepository repository;
    private final IImageService imageService;
    private final IImageRepository imageRepository;


    @Override
    public ProviderResponse save(ProviderRequest request) throws ProviderException, EmailAlreadyExistException {
        Provider entity = new Provider();
        Provider entityForConvert = mapper.convertToEntity(entity, request);
        Provider entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    public ProviderResponse modify(String idProvider, ProviderRequestModify request) throws ProviderException, EmailAlreadyExistException {
        Optional<Provider> optionalProvider = repository.findById(idProvider);
        if (optionalProvider.isPresent()) {
            Provider provider = optionalProvider.get();
            Provider adminForConvert = mapper.convertToEntityModify(provider, request);
            Provider adminForSave = repository.save(adminForConvert);
            return mapper.convertToResponse(adminForSave);
        } else {
            throw new ProviderException(PROVIDER_NOT_FOUND);
        }
    }

    @Override
    public ProviderResponse modifyPassword(String idProvider, ProviderRequestPassword request) throws ProviderException {
        Optional<Provider> providerOptional = repository.findById(idProvider);
        if (providerOptional.isPresent()) {
            Provider provider = providerOptional.get();
            Provider entityForConvert = mapper.convertToEntityModifyPassword(provider, request);
            Provider entityForSave = repository.save(entityForConvert);
            return mapper.convertToResponse(entityForSave);
        } else {
            throw new ProviderException(PROVIDER_NOT_FOUND);
        }
    }

    @Override
    public void delete(String idProvider) throws ProviderException {
        Optional<Provider> optionalAdmin = repository.findById(idProvider);
        if (optionalAdmin.isPresent()) {
            Provider provider = optionalAdmin.get();
            provider.setSoftDelete(!provider.isSoftDelete());
            provider.setUpdateDate(new Date());
            repository.save(provider);
        } else {
            throw new ProviderException(PROVIDER_NOT_FOUND);
        }
    }

    @Override
    public ProviderResponse getById(String idProvider) throws ProviderException {
        if (repository.existsById(idProvider)) {
            Provider provider = repository.getReferenceById(idProvider);
            return mapper.convertToResponse(provider);
        } else {
            throw new ProviderException(PROVIDER_NOT_FOUND);
        }
    }

    @Override
    public List<ProviderResponse> getAll() throws ProviderException {
        List<Provider> providerList = repository.findAll();
        if (!(providerList.isEmpty())) {
            return mapper.convertToResponseList(providerList);
        } else {
            throw new ProviderException("{provider.error.displaying.all.provider}");
        }
    }

    @Override
    public ProviderResponseList getAllxPages(PageRequest request) throws ProviderException {
        Page<Provider> providerPage = repository.searchByHighPage(request);
        if (!(providerPage.isEmpty())) {
            ProviderList providerList = new ProviderList(providerPage.getContent(), request, providerPage.getTotalElements());
            return getProviderResponseList(providerList);
        } else {
            throw new ProviderException("{provider.error.displaying.all.provider}");
        }
    }

    private ProviderResponseList getProviderResponseList(ProviderList providerList) {
        ProviderResponseList response = new ProviderResponseList();

        List<ProviderResponse> providerResponseList = mapper.convertToResponseList(providerList.getContent());
        response.setContent(providerResponseList);

        final int nextPage = providerList.getPageable().next().getPageNumber();
        response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

        final int previousPage = providerList.getPageable().previousOrFirst().getPageNumber();
        response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

        response.setTotalPages(providerList.getTotalPages());
        response.setTotalElements(providerList.getTotalElements());
        return response;
    }

    @Override
    public List<ProviderResponse> getByValue(String value) throws ProviderException {
        if (value == null) {
            value = "";
        }
        List<Provider> providerList = repository.searchByValue("%" + value + "%");
        if (!(providerList.isEmpty())) {
            return mapper.convertToResponseList(providerList);
        } else {
            throw new ProviderException(PROVIDER_NOT_FOUND);
        }
    }

    @Override
    public List<ProviderResponse> getForHigh() throws ProviderException {
        List<Provider> providerList = repository.searchByHigh();
        if (providerList != null) return mapper.convertToResponseList(providerList);
        else {
            throw new ProviderException("{provider.error.displaying.provider.active}");
        }
    }

    @Override
    public void addFileToProvider(String idProvider, MultipartFile multipartFile) throws ProviderException, ImageException {
        Optional<Provider> optionalProvider = repository.findById(idProvider);
        if (optionalProvider.isPresent()) {
            Provider provider = repository.getReferenceById(idProvider);
            if (provider.getImage() != null) {
                throw new ProviderException("{provider.already.contains.image}");
            } else {
                Image image = imageService.saveFile(multipartFile, pathFolderUpload, pathFileUpload);
                image.setProvider(provider);
                provider.setImage(image);
                repository.save(provider);
            }
        } else {
            throw new ProviderException(PROVIDER_NOT_FOUND);
        }
    }

    @Override
    public void removeFileToProvider(String idProvider, String idImage) throws ImageException, ProviderException {
        Optional<Provider> optionalProvider = repository.findById(idProvider);
        if (optionalProvider.isPresent()) {
            Provider provider = repository.getReferenceById(idProvider);
            Optional<Image> optionalFile = imageRepository.findById(idImage);
            if (optionalFile.isPresent()) {
                Image image = imageRepository.getReferenceById(idImage);
                image.setProvider(null);
                provider.setImage(null);
                imageService.deleteFileById(idImage, pathFolderUpload);
                repository.save(provider);
            } else {
                throw new ImageException("{image.not.found}");
            }
        } else {
            throw new ProviderException(PROVIDER_NOT_FOUND);
        }
    }
}