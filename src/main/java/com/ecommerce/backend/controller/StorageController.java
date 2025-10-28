package com.ecommerce.backend.controller;

import com.ecommerce.backend.service.SupabaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/storage")
public class StorageController {

    @Autowired
    private SupabaseStorageService storageService;

    @PostMapping("/upload")
    public Map<String, String> upload(@RequestPart("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        try {
            String url = storageService.uploadFile(
                file.getOriginalFilename(),
                file.getInputStream(),
                file.getContentType(),
                file.getSize()
            );
            response.put("fileDownloadUri", url);
        } catch (Exception e) {
            // Log completo del error en consola
            e.printStackTrace();
            response.put("error", "Error al subir archivo: " + e.getMessage());
        }
        return response;
    }
}
