package com.ibarber.ibarber_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PortfolioDTO {
    private Long id;
    private List<String> imageUrls;
    private Long userId;
    private LocalDateTime createdAt;

    public PortfolioDTO() {
    }

    public PortfolioDTO(Long id, List<String> imageUrls, Long userId, LocalDateTime createdAt) {
        this.id = id;
        this.imageUrls = imageUrls;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
