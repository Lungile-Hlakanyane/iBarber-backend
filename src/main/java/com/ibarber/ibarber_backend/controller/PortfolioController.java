package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.PortfolioService;
import com.ibarber.ibarber_backend.dto.PortfolioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/upload")
    public PortfolioDTO uploadPortfolio(
            @RequestParam Long userId,
            @RequestParam("images") List<MultipartFile> images
    ) {
        return portfolioService.createPortfolio(userId, images);
    }
    @GetMapping("/user/{userId}")
    public List<PortfolioDTO> getPortfolios(@PathVariable Long userId) {
        return portfolioService.getPortfoliosByUserId(userId);
    }

    @GetMapping("/user/{userId}/images")
    public ResponseEntity<List<String>> getPortfolioImagesByUserId(@PathVariable Long userId) {
        List<String> imageUrls = portfolioService.getImageUrlsByUserId(userId);
        return ResponseEntity.ok(imageUrls);
    }
}
