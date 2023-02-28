package com.nocountry.service;

import com.nocountry.exception.ImageException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public interface ICloudinaryService {

    Map uploadImage(MultipartFile multipartFile) throws IOException, ImageException;

    Map modifyImage(String cloudinaryId, MultipartFile multipartFile) throws IOException, ImageException;

    Map deleteImage(String idPublicCloudinary) throws IOException;
}
