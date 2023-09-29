package dev.bhanu.productservice.Repository;

import dev.bhanu.productservice.models.Category;
import dev.bhanu.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    public Optional<Product> findById(UUID id);

    @Query(value = CustomQuery.FIND_ALL_PRODUCT)
    public List<Product> findAllProducts();

    public List<Product> findProductByCategory(Category category);


}
