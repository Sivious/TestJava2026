package com.tests.testjava2026.infrastructure.adapter.out.db;

import com.tests.testjava2026.infrastructure.adapter.out.db.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.Optional;

public interface SpringDataJpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.productId = :productId " +
            "AND p.brandId = :brandId " +
            "AND :applyDate BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC LIMIT 1")
    Optional<PriceEntity> findApplicablePrice(
            @Param("brandId") Integer brandId,
            @Param("productId") Integer productId,
            @Param("applyDate") LocalDateTime applyDate);
}