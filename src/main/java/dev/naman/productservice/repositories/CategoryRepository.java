package dev.naman.productservice.repositories;

import dev.naman.productservice.models.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Lazy
public interface CategoryRepository
extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long uuid);

    @Override
    List<Category> findAllById(Iterable<Long> ids);

    Optional<Category> findByName(String category);
}
