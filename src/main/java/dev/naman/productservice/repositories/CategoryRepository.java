package dev.naman.productservice.repositories;

import dev.naman.productservice.models.Category;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Lazy
public interface CategoryRepository
extends JpaRepository<Category, UUID> {

    Optional<Category> findById(UUID uuid);

    @Override
    List<Category> findAllById(Iterable<UUID> uuids);


    @Query(value = "select name  from category", nativeQuery = true)
     List<String> findAllCategoriesName();

}
