package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.SlotService;
import com.ibarber.ibarber_backend.dto.SlotsDTO;
import com.ibarber.ibarber_backend.entity.Slot;
import com.ibarber.ibarber_backend.entity.User;
import com.ibarber.ibarber_backend.mapper.SlotMapper;
import com.ibarber.ibarber_backend.repository.SlotsRepository;
import com.ibarber.ibarber_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SlotServiceImp implements SlotService {
    @Autowired
    private SlotsRepository slotRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SlotMapper slotMapper;

    @Override
    public Slot createSlot(SlotsDTO slotsDTO) {
        User barber = userRepository.findById(slotsDTO.getBarberId())
                .orElseThrow(() -> new RuntimeException("Barber not found"));

        if (!"barber".equalsIgnoreCase(barber.getRole())) {
            throw new RuntimeException("User is not a barber");
        }

        Slot slot = new Slot();
        slot.setStartTime(slotsDTO.getStartTime());
        slot.setEndTime(slotsDTO.getEndTime());
        slot.setDate(slotsDTO.getDate());
        slot.setBooked(false);
        slot.setBarber(barber);
        return slotRepository.save(slot);
    }
    @Override
    public List<SlotsDTO> getUnBookedSlots() {
        List<Slot> unbookedSlots = slotRepository.findByBookedFalse();
        return unbookedSlots.stream()
                .map(slotMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SlotsDTO> getUnBookedSlotsByBarberId(Long barberId) {
        List<Slot> slots = slotRepository.findByBookedFalseAndBarber_Id(barberId);
        return slots.stream()
                .map(slotMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SlotsDTO> getSlotsByBarberId(Long barberId) {
        List<Slot> slots = slotRepository.findByBarberId(barberId);
        return slots.stream()
                .map(slotMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Slot bookSlot(Long slotId, Long clientId) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));
        if (slot.isBooked()) {
            throw new IllegalStateException("Slot already booked");
        }
        User client = userRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        slot.setBooked(true);
        slot.setClient(client);
        return slotRepository.save(slot);
    }

    @Override
    public Slot approveAppointment(Long slotId) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));
        slot.setApproveAppointment(true);
        return slotRepository.save(slot);
    }

    @Override
    public List<SlotsDTO> getBookedSlotsByBarber(Long barberId) {
        List<Slot> slots = slotRepository.findByBookedTrueAndBarberId(barberId);
        return slots.stream().map(slotMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public long countBookedSlots() {
        return slotRepository.countByBooked(true);
    }
    @Override
    public Long countBookingsByClientId(Long clientId) {
        return slotRepository.countByClient_Id(clientId);
    }
    @Override
    public Slot findLatestSlotByClientId(Long clientId) {
        List<Slot> slots = slotRepository.findTopByClientIdOrderByDateDesc((Pageable) PageRequest.of(0, 1));
        return slots.isEmpty() ? null : slots.get(0);
    }
    @Override
    public Slot getLastSlotByClientId(Long clientId) {
        return slotRepository.findLatestSlotByClientId(clientId);
    }
}
