package com.nocountry.mapper;

import com.nocountry.dto.response.ImageResponse;
import com.nocountry.model.Image;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImageMapper {

    public ImageResponse convertToResponse(Image entity) {
        ImageResponse response = new ImageResponse();
        extractedForConvertToResponse(entity, response);
        return response;
    }

    private static void extractedForConvertToResponse(Image entity, ImageResponse response) {
        response.setId(entity.getId());
        response.setOriginalName(entity.getOriginalName());
        response.setPath(entity.getPath());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String stringCreationDate = sdf.format(entity.getCreationDate());
        String stringUpdateDate;
        if (entity.getUpdateDate() != null) {
            stringUpdateDate = sdf.format(entity.getUpdateDate());
        } else {
            stringUpdateDate = " - ";
        }
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