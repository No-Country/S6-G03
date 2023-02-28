package com.nocountry.controller;

import com.nocountry.exception.ImageException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "IMAGE CONTROLLER")
@Validated
public interface IImageController {

    @PostMapping("/save")
    ResponseEntity<?> save(@RequestParam MultipartFile multipartFile) throws IOException, ImageException;

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id) throws IOException;
}