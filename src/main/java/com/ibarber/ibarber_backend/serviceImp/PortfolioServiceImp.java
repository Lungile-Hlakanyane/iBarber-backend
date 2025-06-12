package com.ibarber.ibarber_backend.serviceImp;
import com.ibarber.ibarber_backend.Service.PortfolioService;
import com.ibarber.ibarber_backend.dto.PortfolioDTO;
import com.ibarber.ibarber_backend.entity.Portfolio;
import com.ibarber.ibarber_backend.mapper.PortfolioMapper;
import com.ibarber.ibarber_backend.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImp implements PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private PortfolioMapper mapper;

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "portfolio" + File.separator;

    @Override
    public PortfolioDTO createPortfolio(Long userId, List<MultipartFile> images) {
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : images) {
            String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
            File file = new File(UPLOAD_DIR + filename);
            file.getParentFile().mkdirs();
            try {
                image.transferTo(file);
                imageUrls.add("/" + UPLOAD_DIR + filename);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store image", e);
            }
        }
        Portfolio portfolio = new Portfolio();
        portfolio.setUserId(userId);
        portfolio.setImageUrls(imageUrls);
        portfolio.setCreatedAt(LocalDateTime.now());
        return mapper.toDTO(portfolioRepository.save(portfolio));
    }

    @Override
    public List<PortfolioDTO> getPortfoliosByUserId(Long userId) {
        List<Portfolio> portfolios = portfolioRepository.findByUserId(userId);
        return portfolios.stream().map(mapper::toDTO).toList();
    }

    @Override
    public List<String> getImageUrlsByUserId(Long userId) {
        List<Portfolio> portfolios = portfolioRepository.findByUserId(userId);
        return portfolios.stream()
                .flatMap(portfolio -> portfolio.getImageUrls().stream()) // flatten the inner lists
                .collect(Collectors.toList());
    }

}
