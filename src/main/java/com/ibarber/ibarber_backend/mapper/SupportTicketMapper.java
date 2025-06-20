package com.ibarber.ibarber_backend.mapper;
import com.ibarber.ibarber_backend.dto.SupportTicketDTO;
import com.ibarber.ibarber_backend.entity.SupportTicket;
import org.springframework.stereotype.Component;

@Component
public class SupportTicketMapper {
    public SupportTicket toEntity(SupportTicketDTO dto) {
        SupportTicket ticket = new SupportTicket();
        ticket.setId(dto.getId());
        ticket.setUsername(dto.getUsername());
        ticket.setSubject(dto.getSubject());
        ticket.setMessage(dto.getMessage());
        ticket.setStatus(dto.getStatus());
        ticket.setCreatedAt(dto.getCreatedAt());
        return ticket;
    }
    public SupportTicketDTO toDto(SupportTicket ticket) {
        SupportTicketDTO dto = new SupportTicketDTO();
        dto.setId(ticket.getId());
        dto.setUsername(ticket.getUsername());
        dto.setSubject(ticket.getSubject());
        dto.setMessage(ticket.getMessage());
        dto.setStatus(ticket.getStatus());
        dto.setCreatedAt(ticket.getCreatedAt());
        return dto;
    }
}
