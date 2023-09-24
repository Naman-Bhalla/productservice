package dev.naman.productservice.services;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductServiceImpl {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    private GenericProductDto convertProductToGenericProductDto(Product product) {

        GenericProductDto genericProduct = new GenericProductDto();
        product.setDescription(genericProduct.getDescription());
        product.setTitle(genericProduct.getTitle());

        return genericProduct;

    }
    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public GenericProductDto getProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            return null;
        }
        return convertProductToGenericProductDto(product.get());
    }


    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        Category category = new Category();
        category.setName("new category");
        Price price = new Price();
        price.setPrice(599.65);
        price.setCurrency("USD");

        Product product1 = new Product();
        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        product1.setImage(product.getImage());
        product1.setCategory(category);
        product1.setPrice(price);

        productRepository.save(product1);

        return convertProductToGenericProductDto(product1);

    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll().stream().collect(Collectors.toList());
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for(Product product : products) {
            genericProductDtos.add(convertProductToGenericProductDto(product));
        }

        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProductById(UUID id) {
        GenericProductDto genericProductDto = new GenericProductDto();
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()) {
            return null;
        }

        productRepository.deleteById(id);
        return convertProductToGenericProductDto(product.get());
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProduct, UUID id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()) {
            return null;
        }
        Product product1 = product.get();
        product1.setTitle(genericProduct.getTitle());
        product1.setDescription(genericProduct.getDescription());
        product1.setImage(genericProduct.getImage());

        productRepository.save(product1);

        return convertProductToGenericProductDto(product1);
    }

    @Override
    public List<ProductDto> getCategoryById(String uuid) {
        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString(uuid));

        if(optionalCategory.isEmpty()) {
            return null;
        }
        Category category = optionalCategory.get();
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : category.getProducts()) {
            ProductDto productDto = new ProductDto();
            productDto.setDescription(product.getDescription());
            productDto.setImage(product.getImage());
            productDto.setTitle(product.getTitle());

            productDtos.add(productDto);
        }
        return  productDtos;

    }

    @Override
    public List<String> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll().stream().collect(Collectors.toList());

        List<Product> products = productRepository.findAllByCategoryIn(categoryList);

        List<String> titles = new ArrayList<>();

        for(Product p : products) {
            titles.add(p.getTitle());
        }

        return titles;
    }
}
