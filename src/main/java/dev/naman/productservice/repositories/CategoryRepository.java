package dev.naman.productservice.repositories;

import dev.naman.productservice.models.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Lazy
public interface CategoryRepository
extends JpaRepository<Category, UUID> {

    Optional<Category> findById(UUID uuid);

    @Override
    List<Category> findAllById(Iterable<UUID> uuids);
}
