package dev.biswajit.ecomm.productservice.repository;

import dev.biswajit.ecomm.productservice.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {
    @Query("SELECT p FROM Price p where abs(p.value-0.01) <= :price and abs(p.value+0.01) >= :price")
    Optional<Price> findByPriceValue(double price);
}
