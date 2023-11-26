package dev.bhanu.productservice.ElasticSearchRepository;

import dev.bhanu.productservice.models.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductElasticSearchRepository extends ElasticsearchRepository<Product, UUID> {
}
