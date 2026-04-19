package com.tests.testjava2026.domain.model;

import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class Price {
    Integer brandId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer priceList;
    Integer productId;
    Integer priority;
    BigDecimal price;
    String curr;
}
