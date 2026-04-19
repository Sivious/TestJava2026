package com.tests.testjava2026.domain.repository;

import com.tests.testjava2026.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {

    Optional<Price> findApplicablePrice(
            Integer brandId,
            Integer productId,
            LocalDateTime applicationDate
    );
}
