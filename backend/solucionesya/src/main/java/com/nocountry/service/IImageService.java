package com.nocountry.service;

import com.nocountry.exception.ImageException;
import com.nocountry.model.Image;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface IImageService {

    @Transactional
    Image saveImage(MultipartFile multipartFile) throws ImageException, IOException;

    @Transactional
    void modifyImage(String idImage, MultipartFile multipartFile) throws ImageException, IOException;

    @Transactional
    void deleteImage(String idImage) throws IOException, ImageException;

    @Transactional(readOnly = true)
    Optional<Image> getImageById(String idImage) throws ImageException;

    @Transactional(readOnly = true)
    List<Image> getAllImage();
}
