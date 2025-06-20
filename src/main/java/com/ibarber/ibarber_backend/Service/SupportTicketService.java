package com.ibarber.ibarber_backend.Service;
import com.ibarber.ibarber_backend.dto.SupportTicketDTO;
import java.util.List;

public interface SupportTicketService {
    SupportTicketDTO createTicket(SupportTicketDTO dto);
    List<SupportTicketDTO> getAllTickets();
    List<SupportTicketDTO> getTicketsByUsername(String username);
    SupportTicketDTO updateTicketStatus(Long id, String status);
}
