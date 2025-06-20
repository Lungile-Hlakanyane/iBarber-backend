package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.SupportTicketService;
import com.ibarber.ibarber_backend.dto.SupportTicketDTO;
import com.ibarber.ibarber_backend.entity.SupportTicket;
import com.ibarber.ibarber_backend.mapper.SupportTicketMapper;
import com.ibarber.ibarber_backend.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupportTicketServiceImp implements SupportTicketService {
    @Autowired
    private SupportTicketRepository supportTicketRepository;
    @Autowired
    private SupportTicketMapper supportTicketMapper;

    @Override
    public SupportTicketDTO createTicket(SupportTicketDTO dto) {
        dto.setStatus("Pending");
        SupportTicket saved = supportTicketRepository.save(supportTicketMapper.toEntity(dto));
        return supportTicketMapper.toDto(saved);
    }

    @Override
    public List<SupportTicketDTO> getAllTickets() {
        return supportTicketRepository.findAll()
                .stream()
                .map(supportTicketMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SupportTicketDTO> getTicketsByUsername(String username) {
        return supportTicketRepository.findByUsername(username)
                .stream()
                .map(supportTicketMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SupportTicketDTO updateTicketStatus(Long id, String status) {
        Optional<SupportTicket> optional = supportTicketRepository.findById(id);
        if (optional.isPresent()) {
            SupportTicket ticket = optional.get();
            ticket.setStatus(status);
            return supportTicketMapper.toDto(supportTicketRepository.save(ticket));
        }
        return null;
    }
}
