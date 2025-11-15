package com.ibarber.ibarber_backend.mapper;
import com.ibarber.ibarber_backend.dto.RatingDTO;
import com.ibarber.ibarber_backend.entity.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {
    public Rating toEntity(RatingDTO dto) {
        Rating rating = new Rating();
        rating.setUserId(dto.getUserId());
        rating.setBarberId(dto.getBarberId());
        rating.setStars(dto.getStars());
        return rating;
    }
    public RatingDTO toDto(Rating rating) {
        RatingDTO dto = new RatingDTO();
        dto.setUserId(rating.getUserId());
        dto.setBarberId(rating.getBarberId());
        dto.setStars(rating.getStars());
        return dto;
    }
}
