package com.tunabel.perfumestorev1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;


@Service
public class FileStorageService {

//    public static final String UPLOAD_SKU = "./images/product";
//    public static final String UPLOAD_AVATAR = "./images/avatar";
//    public static final String UPLOAD_BLOG = "./images/blog";
//    public static final String UPLOAD_OTHER = ${spring.folder_upload_sku:};

    public enum Folder {
        AVATAR, SKU, BLOG, OTHER
    }

    @Value("${spring.folder_upload_other:}")
    private Path rootLocation;

    @Value("${spring.folder_upload_avatar:}")
    private Path avatarImgFolder;

    @Value("${spring.folder_upload_blog:}")
    private Path blogImgFolder;

    @Value("${spring.folder_upload_sku:}")
    private Path skuImgFolder;

    public String store(MultipartFile file, Folder folder) {
        long unixTimestamp = Instant.now().getEpochSecond();

        try {
            String newFileName = ""
                    + unixTimestamp + "-"
                    + file.getOriginalFilename();

            if (folder.equals(Folder.AVATAR)) {
                Files.copy(file.getInputStream(),
                        this.avatarImgFolder.resolve(newFileName));
            } else if (folder.equals(Folder.BLOG)) {
                Files.copy(file.getInputStream(),
                        this.blogImgFolder.resolve(newFileName));
            } else if (folder.equals(Folder.SKU)) {
                Files.copy(file.getInputStream(),
                        this.skuImgFolder.resolve(newFileName));
            } else {
                Files.copy(file.getInputStream(),
                        this.rootLocation.resolve(newFileName));
            }

//            Files.copy(file.getInputStream(),
//                    this.rootLocation.resolve(newFileName));

            return newFileName;
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFile(String filename, Folder folder) {
        try {
            Path file = null;
            if (folder.equals(Folder.AVATAR)) {
                file = avatarImgFolder.resolve(filename);
            } else if (folder.equals(Folder.BLOG)) {
                file = blogImgFolder.resolve(filename);
            } else if (folder.equals(Folder.SKU)) {
                file = skuImgFolder.resolve(filename);
            } else {
                file = rootLocation.resolve(filename);
            }

            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
            Files.createDirectory(avatarImgFolder);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

}
