package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.CustomQueriesImpl;
import dev.naman.productservice.repositories.PriceRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private CustomQueriesImpl customQueriesImpl;

    private CategoryRepository categoryRepository;

    private PriceRepository priceRepository;

    public SelfProductServiceImpl(ProductRepository productRepository,PriceRepository priceRepository,
                                  CustomQueriesImpl customQueriesImpl, CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.customQueriesImpl = customQueriesImpl;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public GenericProductDto getProductById(UUID id) {
        return customQueriesImpl.findProduct(id);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto dto) {
        Category category = null;
        Optional<Category> categoryOptional = categoryRepository.findByName(dto.getCategory());
        if(categoryOptional.isEmpty()){
            category = new Category();
            category.setName(dto.getCategory());
            categoryRepository.save(category);
        }else{
            category = categoryOptional.get();
        }

        Price price = new Price();
        price.setPrice(dto.getPrice());
        price.setCurrency("Dollar");
        priceRepository.save(price);

        Product product = new Product();
        product.setCategory(category);
        product.setPrice(price);
        product.setDescription(dto.getDescription());
        product.setTitle(dto.getTitle());
        product.setImage(dto.getImage());
        productRepository.save(product);

        return dto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return customQueriesImpl.findAllProducts();
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }

    @Override
    public List<GenericProductDto> getProductsCategories(String name) {
        return customQueriesImpl.findProductsByCategory(name);
    }
}
