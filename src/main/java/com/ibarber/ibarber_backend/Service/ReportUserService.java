package com.ibarber.ibarber_backend.Service;
import com.ibarber.ibarber_backend.dto.ReportUserDTO;
import java.util.List;

public interface ReportUserService {
    void reportUser(ReportUserDTO reportUserDTO);
    List<ReportUserDTO> getAllReports();
    // New methods:
    void warnUser(Long reportId);
    void banUser(Long reportId);
    void dismissReport(Long reportId);
    List<ReportUserDTO> getWarningsByUserId(Long userId);
}
