package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.SlotService;
import com.ibarber.ibarber_backend.dto.SlotsDTO;
import com.ibarber.ibarber_backend.entity.Slot;
import com.ibarber.ibarber_backend.entity.User;
import com.ibarber.ibarber_backend.mapper.SlotMapper;
import com.ibarber.ibarber_backend.repository.SlotsRepository;
import com.ibarber.ibarber_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/slots")
public class SlotController {
    @Autowired
    private SlotMapper slotMapper;
    @Autowired
    private SlotsRepository slotsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SlotService slotService;
    @PostMapping("/create")
    public Slot createSlot(@RequestBody SlotsDTO slotsDTO){
        return slotService.createSlot(slotsDTO);
    }
    @GetMapping("/unbooked")
    public ResponseEntity<List<SlotsDTO>> getUnbookedSlots() {
        return ResponseEntity.ok(slotService.getUnBookedSlots());
    }
    @GetMapping("/unbooked/barber/{barberId}")
    public ResponseEntity<List<SlotsDTO>> getUnbookedSlotsByBarber(@PathVariable Long barberId) {
        return ResponseEntity.ok(slotService.getUnBookedSlotsByBarberId(barberId));
    }
    @GetMapping("/barber-id")
    public ResponseEntity<Long> getBarberIdByEmail(@RequestParam String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && "barber".equalsIgnoreCase(user.get().getRole())) {
            return ResponseEntity.ok(user.get().getId()); // Assuming ID is barberId
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/barber/{barberId}")
    public List<SlotsDTO> getSlotsByBarberId(@PathVariable Long barberId) {
        return slotService.getSlotsByBarberId(barberId);
    }

    @PutMapping("/book/{slotId}")
    public ResponseEntity<Slot> bookSlot(@PathVariable Long slotId, @RequestParam Long clientId) {
        try {
            Slot bookedSlot = slotService.bookSlot(slotId, clientId);
            return ResponseEntity.ok(bookedSlot);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/client/{clientId}")
    public List<Slot> getSlotsByClientId(@PathVariable Long clientId) {
        return slotsRepository.findByClientId(clientId);
    }
    @PostMapping("/{id}/approve")
    public ResponseEntity<SlotsDTO> approveSlot(@PathVariable Long id) {
        Slot approvedSlot = slotService.approveAppointment(id);
        return ResponseEntity.ok(slotMapper.toDto(approvedSlot));
    }

    @GetMapping("/slots/booked/barber/{barberId}")
    public List<SlotsDTO> getBookedSlotsByBarber(@PathVariable Long barberId) {
        return slotService.getBookedSlotsByBarber(barberId);
    }

    @GetMapping("/count/booked")
    public ResponseEntity<Long> countBookedSlots() {
        long totalBooked = slotService.countBookedSlots();
        return ResponseEntity.ok(totalBooked);
    }
    @GetMapping("/bookings/count/{clientId}")
    public ResponseEntity<Long> countBookings(@PathVariable Long clientId) {
        Long count = slotService.countBookingsByClientId(clientId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/last-visit/{clientId}")
    public ResponseEntity<?> getLastVisit(@PathVariable Long clientId) {
        Slot lastSlot = slotService.getLastSlotByClientId(clientId);
        if (lastSlot != null) {
            return ResponseEntity.ok(lastSlot.getDate());
        } else {
            return ResponseEntity.ok("No bookings found");
        }
    }
    @GetMapping("/count-by-barber/{barberId}")
    public ResponseEntity<Long> countSlotsByBarberId(@PathVariable Long barberId) {
        long count = slotService.countByBarberId(barberId);
        return ResponseEntity.ok(count);
    }
    @GetMapping("/barber/{barberId}/clients/count")
    public ResponseEntity<Integer> getClientCount(@PathVariable Long barberId) {
        int clientCount = slotService.countDistinctClientsByBarberId(barberId);
        return ResponseEntity.ok(clientCount);
    }

}
