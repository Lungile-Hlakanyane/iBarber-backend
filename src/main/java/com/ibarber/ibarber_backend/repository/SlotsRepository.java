package com.ibarber.ibarber_backend.repository;
import com.ibarber.ibarber_backend.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SlotsRepository extends JpaRepository<Slot, Long> {
    List<Slot> findByBookedFalse();
    List<Slot> findByBookedFalseAndBarber_Id(Long barberId);
    List<Slot> findByBarberId(Long barberId);
    List<Slot> findByClientId(Long clientId);
}
