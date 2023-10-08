package dev.naman.productservice.repositories;

import dev.naman.productservice.models.Product;
import dev.somya.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface SevenApiRepository extends JpaRepository<Product, UUID> {
    @Query(value = "select * from product where category_id = :uuid", nativeQuery = true)
    List<Product> findByCategory(UUID uuid);
    Optional<Product> findById(UUID uuids);

    @Override
    List<Product> findAll();
    ;
}
