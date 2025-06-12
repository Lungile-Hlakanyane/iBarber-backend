package com.ibarber.ibarber_backend.Service;
import com.ibarber.ibarber_backend.dto.PortfolioDTO;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface PortfolioService {
    PortfolioDTO createPortfolio(Long userId, List<MultipartFile> images);
    List<PortfolioDTO> getPortfoliosByUserId(Long userId);

    List<String> getImageUrlsByUserId(Long userId);

}
