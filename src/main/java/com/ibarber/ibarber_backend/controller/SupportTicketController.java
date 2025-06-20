package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.SupportTicketService;
import com.ibarber.ibarber_backend.dto.SupportTicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/support")
public class SupportTicketController {
    @Autowired
    private SupportTicketService supportTicketService;

    @PostMapping
    public ResponseEntity<SupportTicketDTO> createTicket(@RequestBody SupportTicketDTO dto) {
        return ResponseEntity.ok(supportTicketService.createTicket(dto));
    }

    @GetMapping
    public ResponseEntity<List<SupportTicketDTO>> getAllTickets() {
        return ResponseEntity.ok(supportTicketService.getAllTickets());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<SupportTicketDTO>> getTicketsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(supportTicketService.getTicketsByUsername(username));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SupportTicketDTO> updateTicketStatus(@PathVariable Long id, @RequestParam String status) {
        SupportTicketDTO updated = supportTicketService.updateTicketStatus(id, status);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
