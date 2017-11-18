package com.bitirme.taksishare.services;

/**
 * Created by exper on 21.04.2017.
 */
import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.UUID;



@SuppressWarnings("unchecked")
@Component
public class UploadService {

    private String imagePath = "/var/www/html/uploaded";
    private String imageDomain = "http://128.199.50.73/uploaded/";
    private static final Logger log = LogManager.getLogger();

    public String persistFileWithName(String name, MultipartFile file) {
        try {
            int extensionStartIndex = file.getOriginalFilename().lastIndexOf(".");
            if (extensionStartIndex <= 0) {
                return null;
            }
            String extension = file.getOriginalFilename().substring(extensionStartIndex);
            Preconditions.checkNotNull(extension);
            String filename = name + extension;
            File imageFile = new File(imagePath, filename);

            OutputStream imageFileStream = Files.asByteSink(imageFile).openStream();
            ByteStreams.copy(file.getInputStream(), imageFileStream);
            imageFileStream.close();

            String imageUrl = UriComponentsBuilder.fromHttpUrl(imageDomain).path(filename).build().toUriString();
            return imageUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public String persistFile(MultipartFile file) {
        return persistFileWithName(UUID.randomUUID().toString(), file);
    }

    public String persistFile(BufferedImage image) {
        try {
            String fileName = UUID.randomUUID().toString() + ".jpg";
            File imageFile = new File(imagePath, fileName);
            ImageIO.write(image, "jpg", imageFile);

            String imageUrl = UriComponentsBuilder.fromHttpUrl(imageDomain).path(fileName).build().toUriString();
            return imageUrl;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return null;
        }
    }




}
