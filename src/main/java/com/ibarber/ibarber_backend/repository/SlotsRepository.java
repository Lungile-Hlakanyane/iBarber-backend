package com.ibarber.ibarber_backend.repository;
import com.ibarber.ibarber_backend.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
@Repository
public interface SlotsRepository extends JpaRepository<Slot, Long> {
    List<Slot> findByBookedFalse();
    List<Slot> findByBookedFalseAndBarber_Id(Long barberId);
    List<Slot> findByBarberId(Long barberId);
    List<Slot> findByClientId(Long clientId);
    List<Slot> findByBookedTrueAndBarberId(Long barberId);
    long countByBooked(boolean booked);
    Long countByClient_Id(Long clientId);
    @Query("SELECT s FROM Slot s WHERE s.client.id = :clientId ORDER BY s.date DESC LIMIT 1")
    Slot findLatestSlotByClientId(@Param("clientId") Long clientId);
    @Query("SELECT s FROM Slot s WHERE s.client.id = :clientId ORDER BY s.date DESC")
    List<Slot> findTopByClientIdOrderByDateDesc(Pageable pageable);
    long countByBarberId(Long barberId);
    @Query("SELECT COUNT(DISTINCT s.client.id) FROM Slot s WHERE s.barber.id = :barberId")
    int countDistinctClientsByBarberId(@Param("barberId") Long barberId);
}
