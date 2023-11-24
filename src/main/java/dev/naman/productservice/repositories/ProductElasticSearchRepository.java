package dev.naman.productservice.repositories;

import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductElasticSearchRepository extends ElasticsearchRepository<Product, Long> {
    Iterable<Product> findAllByIdAndCategory(Iterable<Long> longs, Category category);

    @Query("{\n" +
            "  \"query\": {\n" +
            "    \"match\": {\n" +
            "      \"FIELD\": \"TEXT\"\n" +
            "    }\n" +
            "  }\n" +
            "}")
    List<Product> findAllByTitleContainingOrDescriptionContaining(String query);
}
