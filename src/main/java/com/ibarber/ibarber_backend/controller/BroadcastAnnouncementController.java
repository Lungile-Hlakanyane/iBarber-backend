package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.BroadcastAnnouncementService;
import com.ibarber.ibarber_backend.dto.BroadcastAnnouncementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class BroadcastAnnouncementController {
    @Autowired
    private BroadcastAnnouncementService service;
    @PostMapping("/send")
    public ResponseEntity<BroadcastAnnouncementDTO> send(@RequestBody BroadcastAnnouncementDTO dto) {
        return ResponseEntity.ok(service.sendAnnouncement(dto));
    }
    @GetMapping("/all")
    public ResponseEntity<List<BroadcastAnnouncementDTO>> getAll() {
        return ResponseEntity.ok(service.getAllAnnouncements());
    }
}
