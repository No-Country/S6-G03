package com.nocountry.service.impl;

import com.nocountry.config.FileStorageProperties;
import com.nocountry.dto.response.ImageResponse;
import com.nocountry.exception.ImageException;
import com.nocountry.mapper.ImageMapper;
import com.nocountry.model.Image;
import com.nocountry.repository.IImageRepository;
import com.nocountry.service.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {

    private static final String REQUEST_WRONG_DATA = "{general.request.wrong.data}";
    private final IImageRepository repository;
    private final ImageMapper mapper;

    @Autowired
    public void fileUploadService(FileStorageProperties fileStorageProperties) throws ImageException {
        Path fileUploadLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileUploadLocation);
        } catch (IOException e) {
            throw new ImageException("{file.the.folder.cannot.be.initialized}");
        }
    }

    @Override
    @Transactional
    public void init(Path pathFolderUpload) throws ImageException {
        try {
            if (!Files.exists(pathFolderUpload)) Files.createDirectory(pathFolderUpload);
        } catch (IOException e) {
            throw new ImageException("{file.the.folder.cannot.be.initialized}");
        }
    }

    @Override
    @Transactional
    public String uploadFiles(MultipartFile multipartFile, Path pathFolderUpload) throws ImageException {
        String originalFileName = multipartFile.getOriginalFilename();
        String newFileName;
        getFileExtension(Objects.requireNonNull(originalFileName));
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
            String stringDate = sdf.format(new Date());
            newFileName = originalFileName.replaceAll(getFileExtension(originalFileName),
                    " - " + stringDate + getFileExtension(originalFileName));
            Files.copy(multipartFile.getInputStream(), pathFolderUpload.resolve(newFileName));
        } catch (IOException e) {
            throw new ImageException("{file.the.file.cannot.be.saved}");
        }
        return newFileName;
    }

    public static String getFileExtension(String originalFileName) {
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    @Override
    @Transactional
    public Image saveFile(MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws ImageException {
        init(pathFolderUpload);
        try {
            Image image = new Image();
            String newFileName = uploadFiles(multipartFile, pathFolderUpload);
            Image entityForConvert = mapper.convertToEntity(image, multipartFile, newFileName, pathFileUpload);
            return repository.save(entityForConvert);
        } catch (ImageException exception) {
            throw new ImageException(REQUEST_WRONG_DATA);
        }
    }

    @Override
    public ImageResponse getFileResponse(Image image) {
        return mapper.convertToResponse(image);
    }

    @Override
    @Transactional
    public List<Image> saveFiles(List<MultipartFile> multipartFiles, Path pathFolderUpload, String pathFileUpload) throws ImageException {
        init(pathFolderUpload);
        try {
            List<Image> imageList = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles) {
                Image image = new Image();
                String newFileName = uploadFiles(multipartFile, pathFolderUpload);
                Image entityForConvert = mapper.convertToEntity(image, multipartFile, newFileName, pathFileUpload);
                Image entityForSave = repository.save(entityForConvert);
                imageList.add((entityForSave));
            }
            return imageList;
        } catch (ImageException exception) {
            throw new ImageException(REQUEST_WRONG_DATA);
        }
    }

    @Override
    @Transactional
    public List<ImageResponse> getFilesResponses(List<Image> imageList) {
        return mapper.convertToResponseList(imageList);
    }

    @Override
    @Transactional
    public Image modifyFile(String id, MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws ImageException {
        try {
            Image entity = repository.getReferenceById(id);
            deleteFileByOriginalName(entity.getImageName(), pathFolderUpload);
            String newFileName = uploadFiles(multipartFile, pathFolderUpload);
            Image entityForConvert = mapper.convertToEntity(entity, multipartFile, newFileName, pathFileUpload);
            return repository.save(entityForConvert);
        } catch (ImageException exception) {
            throw new ImageException("{file.notFound}");
        } catch (Exception exception) {
            throw new ImageException(REQUEST_WRONG_DATA);
        }
    }

    @Override
    @Transactional
    public void deleteFileById(String id, Path pathFolderUpload) throws ImageException {
        Optional<Image> answer = repository.findById(id);
        if (answer.isPresent()) {
            Image entity = answer.get();
            repository.delete(entity);
            deleteFileByOriginalName(entity.getImageName(), pathFolderUpload);
        } else {
            throw new ImageException(REQUEST_WRONG_DATA);
        }
    }

    @Override
    @Transactional
    public String deleteFileByOriginalName(String originalName, Path pathFileUpload) {
        try {
            @SuppressWarnings("unused")
            Boolean delete = Files.deleteIfExists(pathFileUpload.resolve(originalName));
            return "{file.deleted}";
        } catch (IOException e) {
            e.printStackTrace();
            return "{file.error.deleting}";
        }
    }

    @Override
    @Transactional
    public void deleteAll(Path pathFolderUpload) {
        FileSystemUtils.deleteRecursively(pathFolderUpload.toFile());
    }

    @Override
    @Transactional(readOnly = true)
    public Image getById(String id) throws ImageException {
        if (repository.existsById(id)) {
            return repository.getReferenceById(id);
        } else {
            throw new ImageException("{file.notFound}");
        }
    }
}