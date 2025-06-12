package com.ibarber.ibarber_backend.mapper;
import com.ibarber.ibarber_backend.dto.ReportUserDTO;
import com.ibarber.ibarber_backend.entity.ReportUser;
import org.springframework.stereotype.Component;

@Component
public class ReportUserMapper {
    public ReportUser toEntity(ReportUserDTO dto) {
        ReportUser report = new ReportUser();
        report.setReportedUserId(dto.getReportedUserId());
        report.setReporterUserId(dto.getReporterUserId());
        report.setStatus(dto.getStatus());
        report.setTimestamp(dto.getTimestamp());
        report.setReason(dto.getReason());
        return report;
    }
    public ReportUserDTO toDto(ReportUser report) {
        ReportUserDTO dto = new ReportUserDTO();
        dto.setReportedUserId(report.getReportedUserId());
        dto.setStatus(report.getStatus());
        dto.setTimestamp(report.getTimestamp());
        dto.setReporterUserId(report.getReporterUserId());
        dto.setReason(report.getReason());
        return dto;
    }
}
