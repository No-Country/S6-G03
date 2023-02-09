package com.nocountry.controller;

import com.nocountry.dto.response.FileResponse;
import com.nocountry.exception.FileException;
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

@Tag(name = "FILE CONTROLLER")
@Validated
public interface IFileController {

    @PostMapping(path = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file) throws FileException;

    @PostMapping(path = "/upload-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<FileResponse>> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws FileException;

    @PutMapping(path = "/modify-file/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FileResponse> modify(@NotNull @PathVariable("id") String id, @RequestParam("file") MultipartFile file) throws FileException;

    @DeleteMapping(path = "/get-file/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FileResponse> delete(@NotNull @PathVariable("id") String id) throws FileException;

    @DeleteMapping(path = "/delete-file-by-original-name/{filename:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FileResponse> deleteFileByOriginalName(@PathVariable String originalName);

    @GetMapping(path = "/get-file-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FileResponse> getById(@NotNull @PathVariable("id") String id) throws FileException;
}