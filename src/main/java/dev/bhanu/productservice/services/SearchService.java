package dev.bhanu.productservice.services;

import dev.bhanu.productservice.ElasticSearchRepository.ProductElasticSearchRepository;
import dev.bhanu.productservice.Repository.ProductRepository;
import dev.bhanu.productservice.dtos.*;
import dev.bhanu.productservice.models.Product;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private ProductRepository productRepository;
    private ProductElasticSearchRepository productElasticSearchRepository;

    @Autowired
    public SearchService(ProductRepository productRepository, ProductElasticSearchRepository productElasticSearchRepository) {
        this.productRepository = productRepository;
        this.productElasticSearchRepository = productElasticSearchRepository;
    }

    public Page<SelfGenericProductDto> searchProducts(SearchRequestDTO searchRequestDTO) {
        String query = searchRequestDTO.getQuery();
        int pageNumber = searchRequestDTO.getPageNumber();
        int pageSize = searchRequestDTO.getPageSize();
        List<SortParameter> sortParameters = searchRequestDTO.getSortParameters();

        Sort sort = null;

        for (SortParameter sortParameter : sortParameters) {
            Sort curSort = Sort.by(Sort.Direction.fromString(String.valueOf(sortParameter.getSortType())), sortParameter.getParameter());
            if (sort == null) sort = curSort;
            else sort.and(curSort);
        }


        Page<Product> products = productRepository.findByTitleContainingIgnoreCase(query, PageRequest.of(pageNumber, pageSize, sort));
        Page<SelfGenericProductDto> selfGenericProductDtos = products.map(product -> {
            SelfGenericProductDto selfGenericProductDto = new SelfGenericProductDto();
            selfGenericProductDto.setUuid(product.getId());
            selfGenericProductDto.setInventory(product.getInventory());
            selfGenericProductDto.setDescription(product.getDescription());
            selfGenericProductDto.setTitle(product.getTitle());
            selfGenericProductDto.setPrice(product.getPrice());
            selfGenericProductDto.setCategory(product.getCategory().getName());
            return selfGenericProductDto;
        });

        return selfGenericProductDtos;
    }

    public Page<SelfGenericProductDto> SearchProductsByElastic(SearchElasticDTO searchRequestDTO) {
        String query = searchRequestDTO.getQuery();
        int pageNumber = searchRequestDTO.getPageNumber();
        int pageSize = searchRequestDTO.getPageSize();

        Page<Product> products = productElasticSearchRepository.findByTitleOrDescriptionContainingIgnoreCase(query, query, PageRequest.of(pageNumber, pageSize));

        Page<SelfGenericProductDto> selfGenericProductDtos = products.map(product -> {
            SelfGenericProductDto selfGenericProductDto = new SelfGenericProductDto();
            selfGenericProductDto.setUuid(product.getId());
            selfGenericProductDto.setInventory(product.getInventory());
            selfGenericProductDto.setDescription(product.getDescription());
            selfGenericProductDto.setTitle(product.getTitle());
            selfGenericProductDto.setPrice(product.getPrice());
            selfGenericProductDto.setCategory(product.getCategory().getName());
            return selfGenericProductDto;
        });

        return selfGenericProductDtos;
    }
}
