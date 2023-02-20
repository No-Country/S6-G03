package com.nocountry.service.impl;

import com.nocountry.config.ApiConstants;
import com.nocountry.dto.request.ProvisionRequest;
import com.nocountry.dto.request.ProvisionRequestModify;
import com.nocountry.dto.response.ProvisionResponse;
import com.nocountry.dto.response.ProvisionResponseList;
import com.nocountry.exception.ImageException;
import com.nocountry.exception.ProviderException;
import com.nocountry.exception.ProvisionException;
import com.nocountry.list.EPathUpload;
import com.nocountry.mapper.ProvisionMapper;
import com.nocountry.model.Image;
import com.nocountry.model.Provision;
import com.nocountry.model.ProvisionList;
import com.nocountry.repository.IImageRepository;
import com.nocountry.repository.IProvisionRepository;
import com.nocountry.service.IImageService;
import com.nocountry.service.IProvisionService;
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
public class ProvisionServiceImpl implements IProvisionService {

    private final Path pathFolderUpload = Paths.get(EPathUpload.CREATE_PROVISION_FOLDER.toString());
    private final String pathFileUpload = EPathUpload.PATH_PROVISION_IMAGE.toString();
    private static final String PROVISION_NOT_FOUND = "{provision.notFound}";
    private final ProvisionMapper mapper;
    private final IProvisionRepository repository;
    private final IImageService imageService;
    private final IImageRepository imageRepository;

    @Override
    public ProvisionResponse save(ProvisionRequest request) throws ProvisionException, ProviderException {
        Provision entity = new Provision();
        Provision entityForConvert = mapper.convertToEntity(entity, request);
        Provision entityForSave = repository.save(entityForConvert);
        return mapper.convertToResponse(entityForSave);
    }

    @Override
    public ProvisionResponse modify(String idProvision, ProvisionRequestModify request) throws ProvisionException, ProviderException {
        Optional<Provision> optionalProvision = repository.findById(idProvision);
        if (optionalProvision.isPresent()) {
            Provision provision = optionalProvision.get();
            Provision provisionForConvert = mapper.convertToEntityModify(provision, request);
            Provision provisionForSave = repository.save(provisionForConvert);
            return mapper.convertToResponse(provisionForSave);
        } else {
            throw new ProvisionException(PROVISION_NOT_FOUND);
        }
    }

    @Override
    public void delete(String idProvision) throws ProvisionException {
        Optional<Provision> optionalProvision = repository.findById(idProvision);
        if (optionalProvision.isPresent()) {
            Provision provision = optionalProvision.get();
            provision.setSoftDelete(!provision.isSoftDelete());
            provision.setUpdateDate(new Date());
            repository.save(provision);
        } else {
            throw new ProvisionException(PROVISION_NOT_FOUND);
        }
    }

    @Override
    public ProvisionResponse getById(String idProvision) throws ProvisionException {
        if (repository.existsById(idProvision)) {
            Provision provision = repository.getReferenceById(idProvision);
            return mapper.convertToResponse(provision);
        } else {
            throw new ProvisionException(PROVISION_NOT_FOUND);
        }
    }

    @Override
    public List<ProvisionResponse> getAll() throws ProvisionException {
        List<Provision> provisionList = repository.findAll();
        if (!(provisionList.isEmpty())) {
            return mapper.convertToResponseList(provisionList);
        } else {
            throw new ProvisionException("{provision.errorDisplaying.all.provision}");
        }
    }

    @Override
    public ProvisionResponseList getAllxPages(PageRequest request) throws ProvisionException {
        Page<Provision> provisionPage = repository.searchByHighPage(request);
        if (!(provisionPage.isEmpty())) {
            ProvisionList provisionList = new ProvisionList(provisionPage.getContent(), request, provisionPage.getTotalElements());
            return getProvisionResponseList(provisionList);
        } else {
            throw new ProvisionException("{provision.errorDisplaying.all.provision}");
        }
    }

    private ProvisionResponseList getProvisionResponseList(ProvisionList provisionList) {
        ProvisionResponseList response = new ProvisionResponseList();

        List<ProvisionResponse> provisionResponseList = mapper.convertToResponseList(provisionList.getContent());
        response.setContent(provisionResponseList);

        final int nextPage = provisionList.getPageable().next().getPageNumber();
        response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

        final int previousPage = provisionList.getPageable().previousOrFirst().getPageNumber();
        response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

        response.setTotalPages(provisionList.getTotalPages());
        response.setTotalElements(provisionList.getTotalElements());
        return response;
    }

    @Override
    public List<ProvisionResponse> getByValue(String value) throws ProvisionException {
        if (value == null) {
            value = "";
        }
        List<Provision> provisionList = repository.searchByValue("%" + value + "%");
        if (!(provisionList.isEmpty())) {
            return mapper.convertToResponseList(provisionList);
        } else {
            throw new ProvisionException(PROVISION_NOT_FOUND);
        }
    }

    @Override
    public List<ProvisionResponse> getForHigh() throws ProvisionException {
        List<Provision> provisionList = repository.searchByHigh();
        if (provisionList != null) return mapper.convertToResponseList(provisionList);
        else {
            throw new ProvisionException("{provision.error.displaying.admin.active}");
        }
    }

    @Override
    public void addFileToService(String idProvision, MultipartFile multipartFile) throws ProvisionException, ImageException {
        Optional<Provision> optionalProvision = repository.findById(idProvision);
        if (optionalProvision.isPresent()) {
            Provision provision = repository.getReferenceById(idProvision);
            if (provision.getImage() != null) {
                throw new ProvisionException("{provision.already.contains.image}");
            } else {
                Image image = imageService.saveFile(multipartFile, pathFolderUpload, pathFileUpload);
                image.setProvision(provision);
                provision.setImage(image);
                repository.save(provision);
            }
        } else {
            throw new ProvisionException(PROVISION_NOT_FOUND);
        }
    }

    @Override
    public void removeFileToService(String idProvision, String idImage) throws ImageException, ProvisionException {
        Optional<Provision> optionalProvision = repository.findById(idProvision);
        if (optionalProvision.isPresent()) {
            Provision provision = repository.getReferenceById(idProvision);
            Optional<Image> optionalFile = imageRepository.findById(idImage);
            if (optionalFile.isPresent()) {
                Image image = imageRepository.getReferenceById(idImage);
                image.setAdmin(null);
                provision.setImage(null);
                imageService.deleteFileById(idImage, pathFolderUpload);
                repository.save(provision);
            } else {
                throw new ImageException("{image.not.found}");
            }
        } else {
            throw new ProvisionException(PROVISION_NOT_FOUND);
        }
    }
}