package com.nocountry.service.impl;

import com.nocountry.dto.response.FileResponse;
import com.nocountry.exception.FileException;
import com.nocountry.mapper.FileMapper;
import com.nocountry.model.File;
import com.nocountry.repository.IFileRepository;
import com.nocountry.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements IFileService {

    private static final String REQUEST_WRONG_DATA = "{general.request.wrong.data}";
    private final IFileRepository repository;
    private final FileMapper mapper;

    @Override
    @Transactional
    public void init(Path pathFolderUpload) throws FileException {
        try {
            if (!Files.exists(pathFolderUpload)) Files.createDirectory(pathFolderUpload);
        } catch (IOException e) {
            throw new FileException("{file.the.folder.cannot.be.initialized}");
        }
    }

    @Override
    @Transactional
    public String uploadFiles(MultipartFile multipartFile, Path pathFolderUpload) throws FileException {
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
            throw new FileException("{file.the.file.cannot.be.saved}");
        }
        return newFileName;
    }

    public static String getFileExtension(String originalFileName) {
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    @Override
    @Transactional
    public File saveFile(MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws FileException {
        init(pathFolderUpload);
        try {
            File file = new File();
            String newFileName = uploadFiles(multipartFile, pathFolderUpload);
            File entityForConvert = mapper.convertToEntity(file, multipartFile, newFileName, pathFileUpload);
            return repository.save(entityForConvert);
        } catch (FileException exception) {
            throw new FileException(REQUEST_WRONG_DATA);
        }
    }

    @Override
    public FileResponse getFileResponse(File file) {
        return mapper.convertToResponse(file);
    }

    @Override
    @Transactional
    public List<File> saveFiles(List<MultipartFile> multipartFiles, Path pathFolderUpload, String pathFileUpload) throws FileException {
        init(pathFolderUpload);
        try {
            List<File> fileList = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles) {
                File file = new File();
                String newFileName = uploadFiles(multipartFile, pathFolderUpload);
                File entityForConvert = mapper.convertToEntity(file, multipartFile, newFileName, pathFileUpload);
                File entityForSave = repository.save(entityForConvert);
                fileList.add((entityForSave));
            }
            return fileList;
        } catch (FileException exception) {
            throw new FileException(REQUEST_WRONG_DATA);
        }
    }

    @Override
    @Transactional
    public List<FileResponse> getFilesResponses(List<File> fileList) {
        return mapper.convertToResponseList(fileList);
    }

    @Override
    @Transactional
    public File modifyFile(String id, MultipartFile multipartFile, Path pathFolderUpload, String pathFileUpload) throws FileException {
        try {
            File entity = repository.getReferenceById(id);
            deleteFileByOriginalName(entity.getFileName(), pathFolderUpload);
            String newFileName = uploadFiles(multipartFile, pathFolderUpload);
            File entityForConvert = mapper.convertToEntity(entity, multipartFile, newFileName, pathFileUpload);
            return repository.save(entityForConvert);
        } catch (FileException exception) {
            throw new FileException("{file.notFound}");
        } catch (Exception exception) {
            throw new FileException(REQUEST_WRONG_DATA);
        }
    }

    @Override
    @Transactional
    public void deleteFileById(String id, Path pathFolderUpload) throws FileException {
        Optional<File> answer = repository.findById(id);
        if (answer.isPresent()) {
            File entity = answer.get();
            repository.delete(entity);
            deleteFileByOriginalName(entity.getFileName(), pathFolderUpload);
        } else {
            throw new FileException(REQUEST_WRONG_DATA);
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
    public File getById(String id) throws FileException {
        if (repository.existsById(id)) {
            return repository.getReferenceById(id);
        } else {
            throw new FileException("{file.notFound}");
        }
    }
}