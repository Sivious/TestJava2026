package com.tests.testjava2026.infrastructure;

import com.tests.testjava2026.application.service.GetApplicablePriceService;
import com.tests.testjava2026.application.usecase.GetApplicablePriceUseCase;
import com.tests.testjava2026.domain.repository.PriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public GetApplicablePriceUseCase getApplicablePriceUseCase(PriceRepository priceRepository) {
        return new GetApplicablePriceService(priceRepository);
    }
}
