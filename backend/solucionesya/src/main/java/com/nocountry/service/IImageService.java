package com.nocountry.service;

import com.nocountry.dto.response.ImageResponse;
import com.nocountry.exception.ImageException;
import com.nocountry.model.Image;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@Service
public interface IImageService {

    @Transactional
    void init(Path pathFolderUpload) throws ImageException;

    @Transactional
    String uploadFiles(MultipartFile multipartFile, Path pathFolderUpload) throws ImageException;

    @Transactional
    Image saveFile(MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws ImageException;

    ImageResponse getFileResponse(Image image);

    @Transactional
    List<Image> saveFiles(List<MultipartFile> multipartFiles, Path pathFolderUpload, String pathFileUpload) throws ImageException;

    @Transactional
    List<ImageResponse> getFilesResponses(List<Image> imageList);

    @Transactional
    Image modifyFile(String id, MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws ImageException;

    @Transactional
    void deleteFileById(String id, Path pathFolderUpload) throws ImageException;

    @Transactional
    String deleteFileByOriginalName(String originalName, Path pathFileUpload);

    @Transactional
    void deleteAll(Path pathFolderUpload);

    @Transactional(readOnly = true)
    Image getById(String id) throws ImageException;
}