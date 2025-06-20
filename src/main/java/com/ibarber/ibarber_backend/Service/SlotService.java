package com.ibarber.ibarber_backend.Service;
import com.ibarber.ibarber_backend.dto.SlotsDTO;
import com.ibarber.ibarber_backend.entity.Slot;
import java.util.List;

public interface SlotService {
    Slot createSlot(SlotsDTO slotsDTO);
    List<SlotsDTO> getUnBookedSlots();
    List<SlotsDTO> getUnBookedSlotsByBarberId(Long barberId);
    List<SlotsDTO> getSlotsByBarberId(Long barberId);
    public Slot bookSlot(Long slotId, Long clientId);
    Slot approveAppointment(Long slotId);
    List<SlotsDTO> getBookedSlotsByBarber(Long barberId);
    long countBookedSlots();
    Long countBookingsByClientId(Long clientId);
    Slot findLatestSlotByClientId(Long clientId);
    Slot getLastSlotByClientId(Long clientId);
}
