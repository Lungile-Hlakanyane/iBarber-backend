package com.ibarber.ibarber_backend.mapper;
import com.ibarber.ibarber_backend.dto.SlotsDTO;
import com.ibarber.ibarber_backend.entity.Slot;
import org.springframework.stereotype.Component;

@Component
public class SlotMapper {
    public SlotsDTO toDto(Slot slot) {
        SlotsDTO dto = new SlotsDTO();
        dto.setId(slot.getId());
        dto.setStartTime(slot.getStartTime());
        dto.setEndTime(slot.getEndTime());
        dto.setDate(slot.getDate());
        dto.setBarberId(slot.getBarber() != null ? slot.getBarber().getId() : null);
        dto.setClientId(slot.getClient() != null ? slot.getClient().getId() : null);
        dto.setApproveAppointment(slot.getApproveAppointment() != null && slot.getApproveAppointment());
        return dto;
    }

    public void updateSlotFromDto(SlotsDTO dto, Slot slot) {
        if (dto.getStartTime() != null) slot.setStartTime(dto.getStartTime());
        if (dto.getEndTime() != null) slot.setEndTime(dto.getEndTime());
        if (dto.getDate() != null) slot.setDate(dto.getDate());
        slot.setApproveAppointment(dto.isApproveAppointment());
    }
}
