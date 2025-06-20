package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.ReportUserService;
import com.ibarber.ibarber_backend.dto.ReportUserDTO;
import com.ibarber.ibarber_backend.entity.ReportUser;
import com.ibarber.ibarber_backend.entity.User;
import com.ibarber.ibarber_backend.mapper.ReportUserMapper;
import com.ibarber.ibarber_backend.repository.ReportUserRepository;
import com.ibarber.ibarber_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportUserServiceImp implements ReportUserService {
    private final UserRepository userRepository;
    private final ReportUserRepository reportUserRepository;
    private final ReportUserMapper reportUserMapper;
    public ReportUserServiceImp(UserRepository userRepository, ReportUserRepository reportUserRepository, ReportUserMapper reportUserMapper) {
        this.userRepository = userRepository;
        this.reportUserRepository = reportUserRepository;
        this.reportUserMapper = reportUserMapper;
    }
    @Override
    public void reportUser(ReportUserDTO reportUserDTO) {
        ReportUser reportUser = reportUserMapper.toEntity(reportUserDTO);
        reportUser.setTimestamp(LocalDateTime.now());
        reportUserRepository.save(reportUser);
    }

    @Override
    public List<ReportUserDTO> getAllReports() {
        List<ReportUser> reports = reportUserRepository.findAll();
        return reports.stream().map(report -> {
            ReportUserDTO dto = new ReportUserDTO();
            dto.setId(report.getId());
            dto.setReason(report.getReason());
            dto.setReportedUserId(report.getReportedUserId());
            dto.setReporterUserId(report.getReporterUserId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void warnUser(Long reportId) {
        // Optional: log or mark warning in database (if needed)
        System.out.println("Warning user from report ID: " + reportId);
    }

    @Override
    public void banUser(Long reportId) {
        Optional<ReportUser> reportOpt = reportUserRepository.findById(reportId);
        if (reportOpt.isEmpty()) {
            throw new IllegalArgumentException("Report with ID " + reportId + " not found");
        }

        ReportUser report = reportOpt.get();
        Long reportedUserId = report.getReportedUserId();

        Optional<User> userOpt = userRepository.findById(reportedUserId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + reportedUserId + " not found");
        }
        User user = userOpt.get();
        user.setBanned(true);
        userRepository.save(user);
    }
    @Override
    public void dismissReport(Long reportId) {
        ReportUser report = reportUserRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        Long reportedUserId = report.getReportedUserId();
        userRepository.findById(reportedUserId).ifPresent(userRepository::delete);
        reportUserRepository.deleteById(reportId);
    }

    @Override
    public List<ReportUserDTO> getWarningsByUserId(Long userId) {
        List<ReportUser> reports = reportUserRepository.findByReportedUserIdAndStatus(userId, "warned");
        List<ReportUserDTO> warningDTOs = new ArrayList<>();
        for (ReportUser report : reports) {
            ReportUserDTO dto = new ReportUserDTO();
            dto.setId(report.getId());
            dto.setReason(report.getReason());
            dto.setReportedUserId(report.getReportedUserId());
            dto.setReportedUserId(report.getReportedUserId());
            dto.setStatus(report.getStatus());
            dto.setTimestamp(report.getTimestamp());
            warningDTOs.add(dto);
        }
        return warningDTOs;
    }

    @Override
    public Long countWarningsByUserId(Long userId) {
        return reportUserRepository.countByReportedUserIdAndStatus(userId, "warned");
    }
}
