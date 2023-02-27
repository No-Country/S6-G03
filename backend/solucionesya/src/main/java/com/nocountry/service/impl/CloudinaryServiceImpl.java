package com.nocountry.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nocountry.exception.ImageException;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.service.ICloudinaryService;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements ICloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl() {

//        String filename = ".env";
//        String pathRootDirectory = System.getProperty("user.dir");
//        String absolutePath  = pathRootDirectory + File.separator + filename;
//        System.err.println("PATH FILE .env : " + absolutePath );
//
//        System.setProperty("dotenv.path", absolutePath );

        System.setProperty("dotenv.path", "/backend/solucionesya/.env");
        Dotenv dotenv = Dotenv.load();
        cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        cloudinary.config.secure = true;
    }

    @Override
    public Map uploadImage(MultipartFile multipartFile) throws IOException, ImageException {
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        if (bufferedImage == null) {
            throw new ImageException(EExceptionMessage.IMAGE_NOT_VALID.getMessage());
        }
        File file = convert(multipartFile);
        Map params = ObjectUtils.asMap("folder", "upload_image");
        Map result = cloudinary.uploader().upload(file, params);
        file.delete();
        return result;
    }

    @Override
    public Map modifyImage(String cloudinaryId, MultipartFile multipartFile) throws IOException, ImageException {
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        if (bufferedImage == null) {
            throw new ImageException(EExceptionMessage.IMAGE_NOT_VALID.getMessage());
        }
        File file = convert(multipartFile);
        Map params = ObjectUtils.asMap(
                "public_id", cloudinaryId,
                "overwrite", true
        );
        Map result = cloudinary.uploader().upload(file, params);
        file.delete();
        return result;
    }

    @Override
    public Map deleteImage(String idPublicCloudinary) throws IOException {
        return cloudinary.uploader().destroy(idPublicCloudinary, ObjectUtils.emptyMap());
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return file;
    }
}