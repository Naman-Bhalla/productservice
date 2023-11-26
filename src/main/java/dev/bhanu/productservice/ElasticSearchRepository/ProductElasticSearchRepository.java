package dev.bhanu.productservice.ElasticSearchRepository;

import dev.bhanu.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductElasticSearchRepository extends ElasticsearchRepository<Product, UUID> {
    Page<Product> findByTitleOrDescriptionContainingIgnoreCase(String title, String description, Pageable pageable);
}
