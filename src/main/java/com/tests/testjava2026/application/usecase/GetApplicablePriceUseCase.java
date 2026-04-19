package com.tests.testjava2026.application.usecase;

import com.tests.testjava2026.domain.price.PriceEntity;

import java.time.LocalDateTime;

public interface GetApplicablePriceUseCase {
    PriceEntity execute(Integer brandId, Integer productId, LocalDateTime applicationDate);
}
