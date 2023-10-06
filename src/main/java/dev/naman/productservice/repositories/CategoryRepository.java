package dev.naman.productservice.repositories;

import dev.pranay.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findById(UUID uuid);

    @Override
    List<Category> findAllById(Iterable<UUID> uuids);

    Category findByName(String category);

    @Query(nativeQuery = true, value = "Select distinct c.name from category c")
    List<String> listCategoryByName();

}
