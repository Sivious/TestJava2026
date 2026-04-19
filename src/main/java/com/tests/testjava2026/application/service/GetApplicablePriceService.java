package com.tests.testjava2026.application.service;

import com.tests.testjava2026.application.usecase.GetApplicablePriceUseCase;
import com.tests.testjava2026.domain.exception.PriceNotFoundException;
import com.tests.testjava2026.domain.model.Price;
import com.tests.testjava2026.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class GetApplicablePriceService implements GetApplicablePriceUseCase {

    private final PriceRepository priceRepository;

    @Override
    public Price execute(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        return priceRepository.findApplicablePrice(brandId, productId, applicationDate)
                .orElseThrow(() -> new PriceNotFoundException(brandId, productId, applicationDate));
    }
}
