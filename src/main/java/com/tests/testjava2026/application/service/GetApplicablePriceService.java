package com.tests.testjava2026.application.service;

import com.tests.testjava2026.application.usecase.GetApplicablePriceUseCase;
import com.tests.testjava2026.domain.price.PriceEntity;
import com.tests.testjava2026.domain.price.PriceNotFoundException;
import com.tests.testjava2026.repos.PriceRepository;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class GetApplicablePriceService implements GetApplicablePriceUseCase {

    private final PriceRepository priceRepository;

    @Override
    public PriceEntity execute(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        return priceRepository.findApplicablePrice(brandId, productId, applicationDate)
                .orElseThrow(() -> new PriceNotFoundException(brandId, productId, applicationDate));
    }
}
