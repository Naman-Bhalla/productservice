package dev.daliya.productService.services;

import dev.daliya.productService.dtos.GenericProductDto;
import dev.daliya.productService.exeptions.NotFoundException;
import dev.daliya.productService.models.Category;
import dev.daliya.productService.models.Price;
import dev.daliya.productService.models.Product;
import dev.daliya.productService.repositories.CategoryRepository;
import dev.daliya.productService.repositories.PriceRepository;
import dev.daliya.productService.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    public SelfProductServiceImpl(ProductRepository productRepository,
                                  CategoryRepository categoryRepository,
                                  PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        try {
            Product product = productRepository.findById(id).orElseThrow();
            genericProductDto.setId(product.getId());
            genericProductDto.setTitle(product.getTitle());
            genericProductDto.setCategory(product.getCategory().getName());
            genericProductDto.setPrice(product.getPrice().getPrice());
            genericProductDto.setDescription(product.getDescription());
            genericProductDto.setImage(product.getImage());
        } catch (Exception e) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }
        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product product = new Product();
        Price price = new Price();
        price.setPrice(genericProductDto.getPrice());
        product.setPrice(price);
        product.setTitle(genericProductDto.getTitle());
        product.setDescription(genericProductDto.getDescription());
        Category category = new Category();
        category.setName(genericProductDto.getCategory());
        category.setDescription(genericProductDto.getCategory());
        product.setCategory(category);
        Product product1 =  productRepository.save(product);
        genericProductDto.setId(product1.getId());
        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        products.forEach(product -> {
            GenericProductDto genericProductDto = new GenericProductDto();
            genericProductDto.setId(product.getId());
            genericProductDto.setDescription(product.getDescription());
            genericProductDto.setTitle(product.getTitle());
            genericProductDto.setPrice(product.getPrice().getPrice());
            genericProductDto.setCategory(product.getCategory().getName());
            genericProductDto.setImage(product.getImage());
            genericProductDtos.add(genericProductDto);
        });
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProductById(UUID id) throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        try {
            Product product = productRepository.getById(id);
            productRepository.deleteById(id);
            genericProductDto.setId(product.getId());
            genericProductDto.setTitle(product.getTitle());
            genericProductDto.setCategory(product.getCategory().getName());
            genericProductDto.setPrice(product.getPrice().getPrice());
            genericProductDto.setImage(product.getImage());
            genericProductDto.setDescription(product.getDescription());
        } catch (Exception e) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }
        return genericProductDto;
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto, UUID id) throws NotFoundException {
        try {
            Product product = productRepository.findById(id).orElseThrow();
            Category category = categoryRepository.findById(product.getCategory().getId()).orElseThrow();
            category.setName(genericProductDto.getCategory());
            category.setDescription(genericProductDto.getCategory());
            Price price = priceRepository.findById(product.getPrice().getId()).orElseThrow();
            price.setPrice(genericProductDto.getPrice());
            product.setCategory(category);
            product.setPrice(price);
            product.setTitle(genericProductDto.getTitle());
            product.setDescription(genericProductDto.getDescription());
            product.setImage(genericProductDto.getImage());
            genericProductDto.setId(product.getId());
            productRepository.save(product);
        } catch (Exception e) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }
        return genericProductDto;
    }
}
