package com.nocountry.controller;

import com.nocountry.dto.response.ImageResponse;
import com.nocountry.exception.ImageException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "IMAGE CONTROLLER")
@Validated
public interface IImageController {

    @PostMapping(path = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ImageResponse> uploadFile(@RequestParam("image") MultipartFile image) throws ImageException;

    @PostMapping(path = "/upload-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ImageResponse>> uploadFiles(@RequestParam("images") List<MultipartFile> images) throws ImageException;

    @PutMapping(path = "/modify-image/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ImageResponse> modify(@NotNull @PathVariable("id") String id, @RequestParam("image") MultipartFile image) throws ImageException;

    @DeleteMapping(path = "/get-image/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ImageResponse> delete(@NotNull @PathVariable("id") String id) throws ImageException;

    @DeleteMapping(path = "/delete-image-by-original-name/{filename:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ImageResponse> deleteFileByOriginalName(@PathVariable String originalName);

    @GetMapping(path = "/get-image-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ImageResponse> getById(@NotNull @PathVariable("id") String id) throws ImageException;
}