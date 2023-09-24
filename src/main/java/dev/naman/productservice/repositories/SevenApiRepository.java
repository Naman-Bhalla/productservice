package dev.naman.productservice.repositories;

import dev.naman.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SevenApiRepository extends JpaRepository<Product, UUID> {

    @Query(value = "select * from product where category_id = :uuid", nativeQuery = true)
    List<Product> findByCategory(UUID uuid);

}
