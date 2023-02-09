package com.nocountry.service;

import com.nocountry.dto.response.FileResponse;
import com.nocountry.exception.FileException;
import com.nocountry.model.File;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@Service
public interface IFileService {

    @Transactional
    void init(Path pathFolderUpload) throws FileException;

    @Transactional
    String uploadFiles(MultipartFile multipartFile, Path pathFolderUpload) throws FileException;

    @Transactional
    File saveFile(MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws FileException;

    FileResponse getFileResponse(File file);

    @Transactional
    List<File> saveFiles(List<MultipartFile> multipartFiles, Path pathFolderUpload, String pathFileUpload) throws FileException;

    @Transactional
    List<FileResponse> getFilesResponses(List<File> fileList);

    @Transactional
    File modifyFile(String id, MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws FileException;

    @Transactional
    void deleteFileById(String id, Path pathFolderUpload) throws FileException;

    @Transactional
    String deleteFileByOriginalName(String originalName, Path pathFileUpload);

    @Transactional
    void deleteAll(Path pathFolderUpload);

    @Transactional(readOnly = true)
    File getById(String id) throws FileException;
}