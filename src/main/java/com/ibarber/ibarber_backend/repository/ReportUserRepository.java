package com.ibarber.ibarber_backend.repository;
import com.ibarber.ibarber_backend.entity.ReportUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReportUserRepository extends JpaRepository<ReportUser, Long> {
    List<ReportUser> findByReportedUserIdAndStatus(Long reportedUserId, String status);
}
