package com.tests.testjava2026.infrastructure.adapter.out.db;

import com.tests.testjava2026.domain.model.Price;
import com.tests.testjava2026.domain.repository.PriceRepository;
import com.tests.testjava2026.infrastructure.adapter.out.db.entity.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final SpringDataJpaPriceRepository jpaRepository;

    @Override
    public Optional<Price> findApplicablePrice(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        return jpaRepository.findApplicablePrice(brandId, productId, applicationDate)
                .map(this::toDomain);
    }

    private Price toDomain(PriceEntity entity) {
        return Price.builder()
                .brandId(entity.getBrandId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .priceList(entity.getPriceList())
                .productId(entity.getProductId())
                .priority(entity.getPriority())
                .price(entity.getPrice())
                .curr(entity.getCurr())
                .build();
    }
}