package com.nocountry.controller.impl;

import com.nocountry.controller.IFileController;
import com.nocountry.dto.response.FileResponse;
import com.nocountry.exception.FileException;
import com.nocountry.list.EPathUpload;
import com.nocountry.service.IFileService;
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

import static com.nocountry.config.ApiConstants.FILE_URI;

@RestController
@RequestMapping(FILE_URI)
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FileController implements IFileController {

    private final Path pathFolderUpload = Paths.get(EPathUpload.CREATE_FILE_FOLDER.toString());
    private final String pathFileUpload = EPathUpload.PATH_FILE_IMAGE.toString();
    private final IFileService service;

    @Override
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file) throws FileException {
        FileResponse response = service.getFileResponse(service.saveFile(file, pathFolderUpload, pathFileUpload));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FileResponse>> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws FileException {
        List<FileResponse> responseList = service.getFilesResponses(service.saveFiles(files, pathFolderUpload, pathFileUpload));
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FileResponse> modify(@NotNull @PathVariable("id") String id,
                                               @RequestParam("file") MultipartFile file) throws FileException {
        FileResponse response = service.getFileResponse(service.modifyFile(id, file, pathFolderUpload, pathFileUpload));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FileResponse> delete(@NotNull @PathVariable("id") String id) throws FileException {
        service.deleteFileById(id, pathFolderUpload);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<FileResponse> deleteFileByOriginalName(@PathVariable String originalName) {
        service.deleteFileByOriginalName(originalName, pathFolderUpload);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<FileResponse> getById(@NotNull @PathVariable("id") String id) throws FileException {
        FileResponse response = service.getFileResponse(service.getById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}