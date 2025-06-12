package com.ibarber.ibarber_backend.Service;
import com.ibarber.ibarber_backend.dto.BroadcastAnnouncementDTO;
import java.util.List;

public interface BroadcastAnnouncementService {
    BroadcastAnnouncementDTO sendAnnouncement(BroadcastAnnouncementDTO dto);
    List<BroadcastAnnouncementDTO> getAllAnnouncements();
}
