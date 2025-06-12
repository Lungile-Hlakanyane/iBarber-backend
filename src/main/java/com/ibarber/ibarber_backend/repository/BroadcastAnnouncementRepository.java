package com.ibarber.ibarber_backend.repository;
import com.ibarber.ibarber_backend.entity.BroadcastAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadcastAnnouncementRepository extends JpaRepository<BroadcastAnnouncement, Long> {
}
