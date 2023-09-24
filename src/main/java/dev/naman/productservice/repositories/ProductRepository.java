package dev.naman.productservice.repositories;

import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Repository
public interface ProductRepository
extends JpaRepository<Product, UUID> {

    Product findByUuid(UUID uuid);

    Product findByTitleEquals(String title);

    Product findByTitleEqualsAndAndPrice_Price(String title, double price);

    List<Product> findAllByPrice_Currency(String currency);

    @Override
    void delete(Product entity);

    long countAllByPrice_Currency(String currency);

    List<Product> findAllByTitleLike(String titleRegex);

    List<Product> readAllByTitleLike(String titleRegex);

    List<Product> findAllByCategory_NameEquals(String category);

    List<Product> findAllByCategoryIn(List<Category> categories);

    @Query(value = CustomQueries.FIND_ALL_BY_TITLE, nativeQuery = true)
    List<Product> findAllByTitle(String naman);
}
