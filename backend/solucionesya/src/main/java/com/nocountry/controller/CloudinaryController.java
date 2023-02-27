package com.nocountry.controller;

import com.nocountry.exception.ImageException;
import com.nocountry.service.ICloudinaryService;
import com.nocountry.service.IImageService;
import com.nocountry.service.impl.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/cloudinary")
@CrossOrigin
@RequiredArgsConstructor
public class CloudinaryController {

    private final ICloudinaryService service;
    private final IImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException, ImageException {
        imageService.saveImage(multipartFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws IOException {
        Map result = service.deleteImage(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}