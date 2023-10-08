package dev.naman.productservice.repositories;

import dev.naman.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SelfProductRepository extends JpaRepository<Product, Long> {
    Product getProductsById(Long id);

    @Query(value = "select * from Product", nativeQuery = true)
    List<Product> getAllProducts();

    void deleteById(Long id);

    List<Product> getProductsByCategory(String category);

    @Query(value = "select distinct category FROM Product", nativeQuery = true)
    List<String> getAllByCategory();
}
