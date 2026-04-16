package com.tests.testjava2026.repos;

import com.tests.testjava2026.domain.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    Optional<PriceEntity> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime applicationDate);
    Optional<PriceEntity> findByProductIdAndBrandIdAndstartDateGreaterThanAndendDateLessThan(String productId, String brand, String startDate, LocalDateTime applicationDate);
}
