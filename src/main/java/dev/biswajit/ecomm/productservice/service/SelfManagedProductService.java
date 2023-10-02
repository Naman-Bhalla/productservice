package dev.biswajit.ecomm.productservice.service;

import dev.biswajit.ecomm.productservice.dto.ProductDto;
import dev.biswajit.ecomm.productservice.exception.ProductNotFoundException;
import dev.biswajit.ecomm.productservice.model.Category;
import dev.biswajit.ecomm.productservice.model.Currency;
import dev.biswajit.ecomm.productservice.model.Price;
import dev.biswajit.ecomm.productservice.model.Product;
import dev.biswajit.ecomm.productservice.repository.CategoryRepository;
import dev.biswajit.ecomm.productservice.repository.PriceRepository;
import dev.biswajit.ecomm.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Primary
@Service("SELF_STORE_SERVICE")
@ConditionalOnProperty(name = "app.service.type", havingValue = "SELF_STORE_SERVICE")
public class SelfManagedProductService implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    @Autowired
    public SelfManagedProductService(@Autowired ProductRepository productRepository,
                                     CategoryRepository categoryRepository, PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    @Transactional
    public Mono<ProductDto> productBy(Long id) throws ProductNotFoundException {

//        has N+1 query issue since each call to a product will fetch category and price details
        Product productFound = productRepository.findById(id)
                .orElseThrow(() -> ProductNotFoundException.with(String.format("Product with %d not found", id)));
        ProductDto productDto = new ProductDto(productFound.getId(), productFound.getName(), productFound.getPrice().getValue().toString(),
                productFound.getCategory().getTitle(), productFound.getName(), productFound.getImage());

        return Mono.just(productDto);

    }

    @Override
    public Mono<List<ProductDto>> allProducts() {

        return Mono.just(productRepository.findAllProducts()
                .stream()
                .map(productFound -> new ProductDto(productFound.getId(), productFound.getName(), productFound.getPrice().getValue().toString(),
                        productFound.getCategory().getTitle(), productFound.getName(), productFound.getImage()))
                .toList());
    }

    @Override
    @Transactional
    public Mono<ProductDto> add(ProductDto newProduct) {
        Category categoryToBeSaved = categoryRepository.findByTitle(newProduct.getCategory())
                .orElse(new Category(newProduct.getCategory()));
        Price price = new Price(Double.parseDouble(newProduct.getPrice()), Currency.RUPEE);

        Product product = new Product(newProduct.getTitle(),
                categoryToBeSaved, price, newProduct.getImageUrl());

        Product savedProduct = productRepository.save(product);

        return Mono.just(
                new ProductDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice().getValue().toString(),
                        savedProduct.getCategory().getTitle(), savedProduct.getName(), savedProduct.getImage())
        );
    }

    @Override
    @Transactional
    public Mono<ProductDto> deleteBy(Long id) throws ProductNotFoundException {

        Mono<ProductDto> productDto = productBy(id);

        productRepository.deleteById(id);

        return productDto;
    }

    @Override
    @Transactional
    public Mono<ProductDto> updateBy(Long id, ProductDto updateProductDto) throws ProductNotFoundException {
        Mono<ProductDto> productDto = productBy(id);

        Category category = categoryRepository.findByTitle(updateProductDto.getTitle())
                .orElse(new Category(updateProductDto.getCategory()));

        Price price = priceRepository.findByPriceValue(Double.parseDouble(updateProductDto.getPrice()))
                .orElse(new Price(Double.parseDouble(updateProductDto.getPrice()), Currency.RUPEE));

        Mono<Product> updatedProduct = productDto.map(productToBeSaved ->
                        new Product(productToBeSaved.getId(),
                                updateProductDto.getTitle(),
                                category,
                                price,
                                updateProductDto.getImageUrl()))
                .map(productRepository::save);

        return updatedProduct.map(product ->
                new ProductDto(product.getId(), product.getName(), product.getPrice().getValue().toString(),
                        product.getCategory().getTitle(), product.getName(), product.getImage()));
    }

}
