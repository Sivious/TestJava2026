package com.tests.testjava2026.infrastructure.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Sample response for price inquiry")
public class PriceResponse {
    @Schema(description = "Product ID", example = "35455")
    private Integer productId;
    @Schema(description = "Brand ID", example = "1")
    private Integer brandId;
    @Schema(description = "Applied price", example = "1")
    private Integer priceList;
    @Schema(description = "Apply start date", example = "1")
    private LocalDateTime startDate;
    @Schema(description = "Apply end date", example = "1")
    private LocalDateTime endDate;
    @Schema(description = "Price", example = "1")
    private BigDecimal price;
}
