package com.ibarber.ibarber_backend.dto;

public class RatingDTO {
    private Long userId;
    private Long barberId;
    private int stars;

    public RatingDTO() {
    }

    public RatingDTO(Long userId, Long barberId, int stars) {
        this.userId = userId;
        this.barberId = barberId;
        this.stars = stars;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBarberId() {
        return barberId;
    }

    public void setBarberId(Long barberId) {
        this.barberId = barberId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
