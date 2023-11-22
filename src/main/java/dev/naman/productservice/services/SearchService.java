package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<GenericProductDto> searchProducts(String query, int pageNumber, int sizeOfEachPage) {
        Page<Product> productPage = productRepository.findAllByTitleContaining(query, PageRequest.of(pageNumber, sizeOfEachPage));
        List<Product> products = productPage.get().collect(Collectors.toList());

        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (Product product: products) {
            genericProductDtos.add(GenericProductDto.from(product));
        }

        Page<GenericProductDto> genericProductsPage = new PageImpl<>(
                genericProductDtos ,productPage.getPageable(), productPage.getTotalElements());

        return genericProductsPage;
    }
}
