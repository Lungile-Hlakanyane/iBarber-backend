package com.ibarber.ibarber_backend.controller;
import com.ibarber.ibarber_backend.Service.ReportUserService;
import com.ibarber.ibarber_backend.dto.ReportUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/report-user")
public class ReportUserController {

    private final ReportUserService reportUserService;
    @Autowired
    public ReportUserController(ReportUserService reportUserService) {
        this.reportUserService = reportUserService;
    }
    @PostMapping
    public String reportUser(@RequestBody ReportUserDTO reportUserDTO) {
        reportUserService.reportUser(reportUserDTO);
        return "User reported successfully";
    }
    @GetMapping
    public List<ReportUserDTO> getAllReports() {
        return reportUserService.getAllReports();
    }

    @PutMapping("/warn/{reportId}")
    public String warnUser(@PathVariable Long reportId) {
        reportUserService.warnUser(reportId);
        return "User warned successfully";
    }

    @PutMapping("/ban/{reportId}")
    public String banUser(@PathVariable Long reportId) {
        reportUserService.banUser(reportId);
        return "User temporarily deactivated";
    }

    @DeleteMapping("/dismiss/{reportId}")
    public String dismissReport(@PathVariable Long reportId) {
        reportUserService.dismissReport(reportId);
        return "User permanently deleted";
    }
    @GetMapping("/warnings/{userId}")
    public ResponseEntity<List<ReportUserDTO>> getWarningsByUserId(@PathVariable Long userId) {
        List<ReportUserDTO> warnings = reportUserService.getWarningsByUserId(userId);
        return ResponseEntity.ok(warnings);
    }

}