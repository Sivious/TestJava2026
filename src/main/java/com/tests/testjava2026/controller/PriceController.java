package com.tests.testjava2026.controller;

import com.tests.testjava2026.application.usecase.GetApplicablePriceUseCase;
import com.tests.testjava2026.domain.price.PriceEntity;
import com.tests.testjava2026.domain.price.PriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;


import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
@Validated
@RequiredArgsConstructor
@Tag(name = "Prices", description = "Endpoints for price management and search")
public class PriceController {
    private final GetApplicablePriceUseCase getApplicablePriceUseCase;

    @Operation(
            summary = "Get applyed price",
            description = "Get the price with the highest priority value for a product, brand and date."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "No price was found for the parameters provided."),
            @ApiResponse(responseCode = "400", description = "Invalid params")
    })
    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @Parameter(description = "Application date (ISO 8601)", example = "2020-06-14T10:00:00")
            @RequestParam("applyDate") @NotNull(message = "Apply Date is mandatory") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applyDate,
            @Parameter(description = "Product identifier", example = "35455")
            @RequestParam("productId") @NotNull(message = "Product ID is mandatory") @Positive(message = "Product ID must be a positive number") Integer productId,
            @Parameter(description = "Brand identifier (Brand)", example = "1")
            @RequestParam("brandId") @NotNull(message = "Brand ID is mandatory") @Positive(message = "Brand ID must be a positive number") Integer brandId) {

        PriceEntity priceEntity = getApplicablePriceUseCase.execute(brandId, productId, applyDate);
        return ResponseEntity.ok(mapToResponse(priceEntity));
    }

    private PriceResponse mapToResponse(PriceEntity priceEntity) {
        return PriceResponse.builder()
                .productId(priceEntity.getProductId())
                .brandId(priceEntity.getBrandId())
                .priceList(priceEntity.getPriceList())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .price(priceEntity.getPrice())
                .build();
    }

}
