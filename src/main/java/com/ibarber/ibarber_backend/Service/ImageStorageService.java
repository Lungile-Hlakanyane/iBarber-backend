package com.ibarber.ibarber_backend.Service;
import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    String storeImage(MultipartFile file);
}
