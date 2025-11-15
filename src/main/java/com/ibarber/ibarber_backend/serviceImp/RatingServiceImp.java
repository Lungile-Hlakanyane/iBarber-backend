package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.RatingService;
import com.ibarber.ibarber_backend.dto.RatingDTO;
import com.ibarber.ibarber_backend.entity.Rating;
import com.ibarber.ibarber_backend.mapper.RatingMapper;
import com.ibarber.ibarber_backend.repository.RatingRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingServiceImp implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public RatingServiceImp(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }

    @Override
    public RatingDTO rateBarber(RatingDTO dto) {
        Optional<Rating> existing = ratingRepository.findByUserIdAndBarberId(dto.getUserId(), dto.getBarberId());
        Rating rating = existing.orElseGet(Rating::new);
        rating.setUserId(dto.getUserId());
        rating.setBarberId(dto.getBarberId());
        rating.setStars(dto.getStars());
        return ratingMapper.toDto(ratingRepository.save(rating));
    }
    @Override
    public List<RatingDTO> getRatingsForBarber(Long barberId) {
        return ratingRepository.findByBarberId(barberId)
                .stream()
                .map(ratingMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    public double getAverageRating(Long barberId) {
        List<Rating> ratings = ratingRepository.findByBarberId(barberId);
        if (ratings.isEmpty()) return 0.0;
        return ratings.stream().mapToInt(Rating::getStars).average().orElse(0.0);
    }

    @Override
    public Double getAverageRatingForBarber(Long barberId) {
        Double avg = ratingRepository.findAverageRatingByBarberId(barberId);
        return avg != null ? Math.round(avg * 10.0) / 10.0 : 0.0;
    }
}
