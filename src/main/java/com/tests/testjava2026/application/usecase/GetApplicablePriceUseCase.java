package com.tests.testjava2026.application.usecase;

import com.tests.testjava2026.domain.model.Price;

import java.time.LocalDateTime;

public interface GetApplicablePriceUseCase {
    Price execute(Integer brandId, Integer productId, LocalDateTime applicationDate);
}
