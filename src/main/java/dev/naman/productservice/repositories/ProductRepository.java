package dev.naman.productservice.repositories;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.function.Function;

@Repository
public interface ProductRepository
extends JpaRepository<Product, UUID> {
}
