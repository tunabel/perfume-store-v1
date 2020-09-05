package com.tunabel.perfumestorev1.controller.api;

import com.tunabel.perfumestorev1.model.api.FileUploadResult;
import com.tunabel.perfumestorev1.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
            result.setLink("/images/avatar/"+
                    newFilename);
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

}
