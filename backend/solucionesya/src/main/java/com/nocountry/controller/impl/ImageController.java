package com.nocountry.controller.impl;

import com.nocountry.controller.IImageController;
import com.nocountry.dto.response.ImageResponse;
import com.nocountry.exception.ImageException;
import com.nocountry.list.EPathUpload;
import com.nocountry.service.IImageService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.nocountry.config.ApiConstants.IMAGE_URI;

@RestController
@RequestMapping(IMAGE_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ImageController implements IImageController {

    private final Path pathFolderUpload = Paths.get(EPathUpload.CREATE_FILE_FOLDER.toString());
    private final String pathFileUpload = EPathUpload.PATH_FILE_IMAGE.toString();
    private final IImageService service;

    @Override
    public ResponseEntity<ImageResponse> uploadFile(@RequestParam("image") MultipartFile image) throws ImageException {
        ImageResponse response = service.getFileResponse(service.saveFile(image, pathFolderUpload, pathFileUpload));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ImageResponse>> uploadFiles(@RequestParam("images") List<MultipartFile> images) throws ImageException {
        List<ImageResponse> responseList = service.getFilesResponses(service.saveFiles(images, pathFolderUpload, pathFileUpload));
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ImageResponse> modify(@NotNull @PathVariable("id") String id,
                                                @RequestParam("image") MultipartFile image) throws ImageException {
        ImageResponse response = service.getFileResponse(service.modifyFile(id, image, pathFolderUpload, pathFileUpload));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ImageResponse> delete(@NotNull @PathVariable("id") String id) throws ImageException {
        service.deleteFileById(id, pathFolderUpload);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ImageResponse> deleteFileByOriginalName(@PathVariable String originalName) {
        service.deleteFileByOriginalName(originalName, pathFolderUpload);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ImageResponse> getById(@NotNull @PathVariable("id") String id) throws ImageException {
        ImageResponse response = service.getFileResponse(service.getById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}