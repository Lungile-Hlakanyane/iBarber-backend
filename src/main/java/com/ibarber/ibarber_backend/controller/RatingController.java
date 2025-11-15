package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.RatingService;
import com.ibarber.ibarber_backend.dto.RatingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private final RatingService ratingService;
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }
    @PostMapping
    public ResponseEntity<RatingDTO> rateBarber(@RequestBody RatingDTO dto) {
        return ResponseEntity.ok(ratingService.rateBarber(dto));
    }
    @GetMapping("/barber/{barberId}")
    public ResponseEntity<List<RatingDTO>> getRatingsForBarber(@PathVariable Long barberId) {
        return ResponseEntity.ok(ratingService.getRatingsForBarber(barberId));
    }


    @GetMapping("/average/{barberId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long barberId) {
        Double averageRating = ratingService.getAverageRatingForBarber(barberId);
        return ResponseEntity.ok(averageRating);
    }
}
