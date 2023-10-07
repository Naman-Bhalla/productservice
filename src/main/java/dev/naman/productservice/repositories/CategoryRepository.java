package dev.naman.productservice.repositories;

import dev.naman.productservice.models.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Lazy
public interface CategoryRepository
extends JpaRepository<Category, UUID> {

    @Override
    Optional<Category> findById(UUID uuid);

    @Override
    List<Category> findAllById(Iterable<UUID> uuids);

    // get category name from category id
    @Query(value = "SELECT name FROM Category WHERE id = :id", nativeQuery = true)
    String findNameById(@Param("id") UUID id);

    @Query(value = "SELECT id FROM Product WHERE category IN (SELECT id FROM Category WHERE name = :category)", nativeQuery = true)
    List<String> findAllByCategoryIn(@Param("category") String category);

    @Query("SELECT c FROM category c WHERE LOWER(c.name) = LOWER(:name)")
    Optional<Category> findByNameIgnoreCase(String name);

}
