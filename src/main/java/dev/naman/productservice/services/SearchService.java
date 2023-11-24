package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.SortParameter;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.ProductElasticSearchRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
//    private ProductRepository productRepository;

    private ProductElasticSearchRepository elasticSearchRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // {"price", "title", "rating"}
    public Page<GenericProductDto> searchProducts(String query, int pageNumber, int sizeOfEachPage,
                                                  List<SortParameter> sortByParameters) {

//        Sort sort = Sort.by("title").descending()
//                .and(
//                        Sort.by("price").descending()
//                )
//                .and(
//                        Sort.by("averageRating").ascending()
//                );

        Sort sort;

        if (sortByParameters.get(0).getSortType().equals("ASC")) {
            sort = Sort.by(sortByParameters.get(0).getParameterName());
        } else {
            sort = Sort.by(sortByParameters.get(0).getParameterName()).descending();
        }

        for (int i = 1; i < sortByParameters.size(); ++i) {
            if (sortByParameters.get(i).getSortType().equals("ASC")) {
                sort = sort.and(Sort.by(sortByParameters.get(i).getParameterName()));
            } else {
                sort = sort.and(
                        Sort.by(sortByParameters.get(i).getParameterName()).descending()
                );
            }
        }

        Page<Product> productPage = productRepository.findAllByTitleContaining(query,
                PageRequest.of(pageNumber, sizeOfEachPage, sort));
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
