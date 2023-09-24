package dev.naman.productService.repositories;

import dev.naman.productService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Override
    Optional<Product> findById(UUID uuid);

}
