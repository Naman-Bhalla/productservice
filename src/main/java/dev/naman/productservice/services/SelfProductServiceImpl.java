package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CustomQueriesImpl;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private CustomQueriesImpl customQueriesImpl;

    public SelfProductServiceImpl(ProductRepository productRepository, CustomQueriesImpl customQueriesImpl) {

        this.productRepository = productRepository;
        this.customQueriesImpl = customQueriesImpl;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return customQueriesImpl.findAllProducts();
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }
}
