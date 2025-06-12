package com.ibarber.ibarber_backend.mapper;
import com.ibarber.ibarber_backend.dto.PortfolioDTO;
import com.ibarber.ibarber_backend.entity.Portfolio;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMapper {
    public Portfolio toEntity(PortfolioDTO dto) {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(dto.getId());
        portfolio.setImageUrls(dto.getImageUrls());
        portfolio.setUserId(dto.getUserId());
        portfolio.setCreatedAt(dto.getCreatedAt());
        return portfolio;
    }

    public PortfolioDTO toDTO(Portfolio entity) {
        PortfolioDTO dto = new PortfolioDTO();
        dto.setId(entity.getId());
        dto.setImageUrls(entity.getImageUrls());
        dto.setUserId(entity.getUserId());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
