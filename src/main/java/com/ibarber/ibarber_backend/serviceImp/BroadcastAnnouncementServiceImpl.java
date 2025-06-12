package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.BroadcastAnnouncementService;
import com.ibarber.ibarber_backend.dto.BroadcastAnnouncementDTO;
import com.ibarber.ibarber_backend.entity.BroadcastAnnouncement;
import com.ibarber.ibarber_backend.mapper.BroadcastAnnouncementMapper;
import com.ibarber.ibarber_backend.repository.BroadcastAnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BroadcastAnnouncementServiceImpl implements BroadcastAnnouncementService {
    @Autowired
    private BroadcastAnnouncementRepository repository;
    @Autowired
    private BroadcastAnnouncementMapper mapper;

    @Override
    public BroadcastAnnouncementDTO sendAnnouncement(BroadcastAnnouncementDTO dto) {
        BroadcastAnnouncement saved = repository.save(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Override
    public List<BroadcastAnnouncementDTO> getAllAnnouncements() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
