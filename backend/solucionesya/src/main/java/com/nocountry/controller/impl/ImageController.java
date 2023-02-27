package com.nocountry.controller.impl;

import com.nocountry.controller.IImageController;
import com.nocountry.exception.ImageException;
import com.nocountry.service.ICloudinaryService;
import com.nocountry.service.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static com.nocountry.config.ApiConstants.IMAGE_URI;

@RestController
@RequestMapping(IMAGE_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ImageController implements IImageController {

    private final ICloudinaryService service;
    private final IImageService imageService;

    @Override
    public ResponseEntity<?> save(@RequestParam MultipartFile multipartFile) throws IOException, ImageException {
        imageService.saveImage(multipartFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws IOException {
        Map result = service.deleteImage(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}