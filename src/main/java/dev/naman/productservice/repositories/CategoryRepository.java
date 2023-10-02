package dev.naman.productservice.repositories;

import dev.naman.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    public <S extends Category> S save(S entity);
    public Optional<Category> findById(UUID uuid);
    public Optional<Category> findByName(String name);
}