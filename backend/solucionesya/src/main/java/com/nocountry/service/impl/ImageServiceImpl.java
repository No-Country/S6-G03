package com.nocountry.service.impl;

import com.nocountry.exception.ImageException;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.model.Image;
import com.nocountry.repository.IImageRepository;
import com.nocountry.service.ICloudinaryService;
import com.nocountry.service.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {

    private final IImageRepository repository;
    private final ICloudinaryService cloudinaryService;

    @Override
    public Image saveImage(MultipartFile multipartFile) throws ImageException, IOException {
        try {
            Map result = cloudinaryService.uploadImage(multipartFile);
            Image image = new Image((String) result.get("original_filename"),
                    (String) result.get("url"),
                    (String) result.get("public_id"));
            repository.save(image);
            return image;
        } catch (ImageException exception) {
            throw new ImageException(EExceptionMessage.REQUEST_WRONG_DATA.getMessage());
        }
    }

    @Override
    public void modifyImage(String idImage, MultipartFile multipartFile) throws ImageException, IOException {
        Optional<Image> imageOptional = repository.findById(idImage);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            Map result = cloudinaryService.modifyImage(image.getCloudinaryId(), multipartFile);
            image.setOriginalName((String) result.get("original_filename"));
            image.setPath((String) result.get("url"));
            image.setCloudinaryId((String) result.get("public_id"));
            image.setUpdateDate(new Date());
            repository.save(image);
        } else {
            throw new ImageException(EExceptionMessage.IMAGE_NOT_FOUND.getMessage());
        }
    }

    @Override
    public void deleteImage(String idImage) throws IOException, ImageException {
        Optional<Image> imageOptional = repository.findById(idImage);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            cloudinaryService.deleteImage(image.getCloudinaryId());
            repository.deleteById(idImage);
        } else {
            throw new ImageException(EExceptionMessage.IMAGE_NOT_FOUND.getMessage());
        }
    }

    public Optional<Image> getImageById(String idImage) throws ImageException {
        Optional<Image> imageOptional = repository.findById(idImage);
        if (imageOptional.isPresent()) {
            return repository.findById(idImage);
        } else {
            throw new ImageException(EExceptionMessage.IMAGE_NOT_FOUND.getMessage());
        }
    }

    public List<Image> getAllImage() {
        return repository.findAll();
    }
}