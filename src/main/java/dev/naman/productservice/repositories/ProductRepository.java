package dev.naman.productservice.repositories;

import dev.naman.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    <S extends Product> S save (S entity);
    List<Product> findAll();
    Optional<Product> findById(UUID uuid);
    void deleteById(UUID id);
    List<Product> findAllByCategory_Name(String name);
}