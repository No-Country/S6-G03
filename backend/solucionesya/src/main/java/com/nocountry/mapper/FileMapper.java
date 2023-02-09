package com.nocountry.mapper;

import com.nocountry.dto.response.FileResponse;
import com.nocountry.model.File;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileMapper {

    public File convertToEntity(File entity, MultipartFile file, String newFileName, String pathFileUpload) {
        extractedForConvertToEntity(entity, file, newFileName, pathFileUpload);
        return entity;
    }

    private static void extractedForConvertToEntity(File entity, MultipartFile file, String newFileName, String pathFileUpload) {
        String path = pathFileUpload + newFileName;
        entity.setOriginalName(file.getOriginalFilename());
        entity.setFileName(newFileName);
        entity.setPath(path);
    }

    public FileResponse convertToResponse(File entity) {
        FileResponse response = new FileResponse();
        extractedForConvertToResponse(entity, response);
        return response;
    }

    private static void extractedForConvertToResponse(File entity, FileResponse response) {
        response.setId(entity.getId());
        response.setOriginalName(entity.getOriginalName());
        response.setFileName(entity.getFileName());
        response.setPath(entity.getPath());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringCreationDate = sdf.format(entity.getCreationDate());
        String stringUpdateDate = sdf.format(entity.getUpdateDate());
        response.setCreationDate(stringCreationDate);
        response.setUpdateDate(stringUpdateDate);
    }

    public List<FileResponse> convertToResponseList(List<File> list) {
        List<FileResponse> responseList = new ArrayList<>();
        for (File entity : list) {
            responseList.add(this.convertToResponse(entity));
        }
        return responseList;
    }
}