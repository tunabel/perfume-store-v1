package com.tunabel.perfumestorev1.controller.api;

import com.tunabel.perfumestorev1.model.api.BaseApiResult;
import com.tunabel.perfumestorev1.model.api.DataApiResult;
import com.tunabel.perfumestorev1.model.api.FileUploadResult;
import com.tunabel.perfumestorev1.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/upload")
public class UploadApiController {

    @Autowired
    FileStorageService storageService;

    @PostMapping("/upload-image")
    public FileUploadResult uploadImage(
            @RequestParam("file") MultipartFile file) {
        String message = "";
        FileUploadResult result = new FileUploadResult();
        try {
            storageService.store(file, FileStorageService.Folder.OTHER);

            String newFilename = storageService.store(file, FileStorageService.Folder.OTHER);
            message = "You successfully uploaded " +
                    file.getOriginalFilename() + "!";
            result.setMessage(message);
            result.setSuccessful(true);
            result.setLink("/link/" +
                    newFilename);
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/upload-avatar")
    public FileUploadResult uploadAvatar(
            @RequestParam("file") MultipartFile file) {
        String message = "";
        FileUploadResult result = new FileUploadResult();
        try {
            String newFilename = storageService.store(file, FileStorageService.Folder.AVATAR);
            message = "You successfully uploaded " +
                    file.getOriginalFilename() + "!";
            result.setMessage(message);
            result.setSuccessful(true);
            result.setLink("/images/avatar/" +
                    newFilename);
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/upload-skuimg")
    public FileUploadResult uploadSkuImage(
            @RequestParam("file") MultipartFile file) {
        String message = "";
        FileUploadResult result = new FileUploadResult();
        try {
            String newFilename = storageService.store(file, FileStorageService.Folder.SKU);
            message = "You successfully uploaded " +
                    file.getOriginalFilename() + "!";
            result.setMessage(message);
            result.setSuccessful(true);
            result.setLink("/images/product/" +
                    newFilename);
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/upload-blogimg")
    public FileUploadResult uploadBlogImage(
            @RequestParam("file") MultipartFile file) {
        String message = "";
        FileUploadResult result = new FileUploadResult();
        try {
            String newFilename = storageService.store(file, FileStorageService.Folder.BLOG);
            message = "You successfully uploaded " +
                    file.getOriginalFilename() + "!";
            result.setMessage(message);
            result.setSuccessful(true);
            result.setLink("/images/blog/" +
                    newFilename);
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/delete")
    public BaseApiResult deleteImage(
            @RequestParam("filePath") String filePath) {
        DataApiResult result = new DataApiResult();
        try {
            String correctPath = "./src/main/resources/static/" + filePath.substring(5);
            storageService.deleteOne(correctPath);
            result.setSuccessful(true);
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
