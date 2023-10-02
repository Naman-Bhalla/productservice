package dev.biswajit.ecomm.productservice.repository;

import dev.biswajit.ecomm.productservice.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository(value = "PRODUCT_REPOSITORY")
public interface ProductRepository extends JpaRepository<Product, Long> {

//    Mono<Product> findProductById(Long id);

    @Query("select p from Product p join fetch p.category c join fetch p.price pr where c.title=:title")
    List<Product> findProductsByCategory(@Param("title") String title);

    @Query("select p from Product p join fetch p.category c join fetch p.price pr")
    List<Product> findAllProducts();
}
