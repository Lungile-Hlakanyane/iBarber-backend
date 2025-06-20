package com.ibarber.ibarber_backend.repository;
import com.ibarber.ibarber_backend.entity.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
    List<SupportTicket> findByUsername(String username);
}
