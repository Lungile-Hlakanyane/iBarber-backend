package com.ibarber.ibarber_backend.repository;
import com.ibarber.ibarber_backend.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    List<Rating> findByBarberId(Long barberId);
    Optional<Rating> findByUserIdAndBarberId(Long userId, Long barberId);
    @Query("SELECT AVG(r.stars) FROM Rating r WHERE r.barberId = :barberId")
    Double findAverageRatingByBarberId(@Param("barberId") Long barberId);
}
