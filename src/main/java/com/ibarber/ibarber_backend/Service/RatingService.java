package com.ibarber.ibarber_backend.Service;
import com.ibarber.ibarber_backend.dto.RatingDTO;
import java.util.List;

public interface RatingService {
    RatingDTO rateBarber(RatingDTO dto);
    List<RatingDTO> getRatingsForBarber(Long barberId);
    double getAverageRating(Long barberId);
    Double getAverageRatingForBarber(Long barberId);
}
