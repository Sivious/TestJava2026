package com.tests.testjava2026.controller;

import com.tests.testjava2026.application.usecase.GetApplicablePriceUseCase;
import com.tests.testjava2026.domain.DTO.PriceResponse;
import com.tests.testjava2026.domain.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController {
    private final GetApplicablePriceUseCase getApplicablePriceUseCase;

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam("applyDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applyDate,
            @RequestParam("productId") Integer productId,
            @RequestParam("brandId") Integer brandId) {

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
