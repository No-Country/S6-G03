package com.nocountry.mapper;

import com.nocountry.dto.response.ImageResponse;
import com.nocountry.model.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImageMapper {

    public Image convertToEntity(Image entity, MultipartFile image, String newFileName, String pathFileUpload) {
        extractedForConvertToEntity(entity, image, newFileName, pathFileUpload);
        return entity;
    }

    private static void extractedForConvertToEntity(Image entity, MultipartFile image, String newFileName, String pathFileUpload) {
        String path = pathFileUpload + newFileName;
        entity.setOriginalName(image.getOriginalFilename());
        entity.setImageName(newFileName);
        entity.setPath(path);
    }

    public ImageResponse convertToResponse(Image entity) {
        ImageResponse response = new ImageResponse();
        extractedForConvertToResponse(entity, response);
        return response;
    }

    private static void extractedForConvertToResponse(Image entity, ImageResponse response) {
        response.setId(entity.getId());
        response.setOriginalName(entity.getOriginalName());
        response.setImageName(entity.getImageName());
        response.setPath(entity.getPath());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringCreationDate = sdf.format(entity.getCreationDate());
        String stringUpdateDate = sdf.format(entity.getUpdateDate());
        response.setCreationDate(stringCreationDate);
        response.setUpdateDate(stringUpdateDate);
    }

    public List<ImageResponse> convertToResponseList(List<Image> list) {
        List<ImageResponse> responseList = new ArrayList<>();
        for (Image entity : list) {
            responseList.add(this.convertToResponse(entity));
        }
        return responseList;
    }
}