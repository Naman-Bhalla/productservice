package dev.naman.productservice.repositories;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SelfProductRepository extends JpaRepository<Product,Long> {

    @Override
    Optional<Product> findById(Long aLong);

    <S extends Product> S save(GenericProductDto entity);

    @Override
    List<Product> findAll();

    @Override
    void deleteById(Long aLong);
}
