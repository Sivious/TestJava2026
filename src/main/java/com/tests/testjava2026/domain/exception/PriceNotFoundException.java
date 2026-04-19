package com.tests.testjava2026.domain.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        super("Price not found for brand: " + brandId + ", product: " + productId + " and date: " + applicationDate);
    }
}