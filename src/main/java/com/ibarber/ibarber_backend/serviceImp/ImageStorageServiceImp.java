package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.ImageStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageStorageServiceImp implements ImageStorageService {
    private final Path storageDirectory;
    public ImageStorageServiceImp(@Value("${app.upload.dir}") String uploadDir) {
        this.storageDirectory = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.storageDirectory);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create upload directory!", ex);
        }
    }
    @Override
    public String storeImage(MultipartFile file) {
        try {
            String uploadDir = "/home/ec2-user/uploads/";
            Files.createDirectories(Paths.get(uploadDir));
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, file.getBytes());
            return "/uploads/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("Failed to store image: " + e.getMessage(), e);
        }
    }
    private String getFileExtension(String filename) {
        String[] parts = filename.split("\\.");
        return parts.length > 1 ? parts[parts.length - 1] : "";
    }
}
