package dev.bhanu.productservice.Repository;

import dev.bhanu.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query(value = CustomQuery.FIND_ALL_CATEGORY, nativeQuery = true)
    List<Category> getAllCategory();

    Optional<Category> getCategoryByName(String name);

}
