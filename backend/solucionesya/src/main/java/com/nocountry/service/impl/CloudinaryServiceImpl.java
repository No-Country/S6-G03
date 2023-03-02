package com.nocountry.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nocountry.exception.CloudinaryException;
import com.nocountry.exception.ImageException;
import com.nocountry.list.EExceptionMessage;
import com.nocountry.service.ICloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Service
public class CloudinaryServiceImpl implements ICloudinaryService {

    private static final String APPLICATION_PROPERTIES = "/application.properties";
    private static final String CLOUDINARY_URL = "cloudinary.url";
    private final Cloudinary cloudinary;
    private final String cloudinaryUrl;

    public CloudinaryServiceImpl() throws CloudinaryException {
        try (InputStream input = getClass().getResourceAsStream(APPLICATION_PROPERTIES)) {
            Properties prop = new Properties();
            if (input == null) {
                throw new CloudinaryException(EExceptionMessage.UNABLE_TO_FIND_APPLICATION_PROPERTIES_FILE.getMessage());
            }
            prop.load(input);
            cloudinaryUrl = prop.getProperty(CLOUDINARY_URL);
        } catch (Exception exception) {
            throw new CloudinaryException(EExceptionMessage.UNABLE_TO_FIND_APPLICATION_PROPERTIES_FILE.getMessage() + exception.getMessage());
        }
        cloudinary = new Cloudinary(cloudinaryUrl);
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
                "overwrite", true);
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