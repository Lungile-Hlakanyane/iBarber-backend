package com.ibarber.ibarber_backend.mapper;
import com.ibarber.ibarber_backend.dto.BroadcastAnnouncementDTO;
import com.ibarber.ibarber_backend.entity.BroadcastAnnouncement;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;

@Component
public class BroadcastAnnouncementMapper {
    public BroadcastAnnouncement toEntity(BroadcastAnnouncementDTO dto) {
        BroadcastAnnouncement announcement = new BroadcastAnnouncement();
        announcement.setTitle(dto.getTitle());
        announcement.setMessage(dto.getMessage());
        announcement.setTargetGroup(dto.getTargetGroup());
        announcement.setCreatedAt(java.time.LocalDateTime.now());
        announcement.setCreatedBy(dto.getCreatedBy());
        return announcement;
    }
    public BroadcastAnnouncementDTO toDto(BroadcastAnnouncement entity) {
        BroadcastAnnouncementDTO dto = new BroadcastAnnouncementDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setMessage(entity.getMessage());
        dto.setTargetGroup(entity.getTargetGroup());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setDate(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return dto;
    }
}
