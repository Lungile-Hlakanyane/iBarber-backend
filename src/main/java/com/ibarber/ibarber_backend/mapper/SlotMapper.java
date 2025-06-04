package com.ibarber.ibarber_backend.mapper;
import com.ibarber.ibarber_backend.dto.SlotsDTO;
import com.ibarber.ibarber_backend.entity.Slot;
import org.springframework.stereotype.Component;

@Component
public class SlotMapper {
    public SlotsDTO toDto(Slot slot) {
        SlotsDTO dto = new SlotsDTO();
        dto.setStartTime(slot.getStartTime());
        dto.setId(slot.getId()); //map Id
        dto.setEndTime(slot.getEndTime());
        dto.setDate(slot.getDate());
        dto.setBarberId(slot.getBarber() != null ? slot.getBarber().getId() : null);
        return dto;
    }
}
