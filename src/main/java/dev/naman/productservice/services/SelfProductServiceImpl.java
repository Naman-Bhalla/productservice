package dev.naman.productservice.services;

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
@Service("selfProductServiceImpl") //it tells spring that this is service class with business logic
public class SelfProductServiceImpl implements ProductServiceImpl {

    private ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductServiceImpl(ProductRepository productRepository,
                                  CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public GenericProductDto convertProductToGenericProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setTitle(product.getTitle());

        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        // create Category obj and set its values
        Category category = new Category();
        category.setName("new category");
        // create Price obj and set its values
        Price price = new Price();
        price.setPrice(134.99);
        price.setCurrency("USD");
        // Create project obj and set its values along with category and price obj
        Product product1 = new Product();
        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        product1.setImage(product.getImage());
        product1.setCategory(category);
        product1.setPrice(price);

        productRepository.save(product1); // save product obj in db

        return convertProductToGenericProductDto(product1);
    }

    @Override
    public GenericProductDto getProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
            return null;
        }
        return convertProductToGenericProductDto(product.get());
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll().stream()
                .collect(Collectors.toList());
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for (Product product: products){
            genericProductDtos.add(convertProductToGenericProductDto(product));
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProductById(UUID id) {
        GenericProductDto genericProductDto = new GenericProductDto();
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            return null;
        }

        productRepository.deleteById(id);
        return convertProductToGenericProductDto(product.get());
    }

    @Override
    public GenericProductDto updatePrductById(GenericProductDto genericProductDto, UUID id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            return null;
        }
        Product product1 = product.get();
        product1.setTitle(genericProductDto.getTitle());
        product1.setDescription(genericProductDto.getDescription());
        product1.setImage(genericProductDto.getImage());

        productRepository.save(product1);
        return  convertProductToGenericProductDto(product1);
    }

    public List<ProductDto> getCategoryById(String uuid){
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));

        if (categoryOptional.isEmpty()){
            return null;
        }
        Category category = categoryOptional.get();

        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : category.getProducts()){
            ProductDto productDto = new ProductDto();
            productDto.setDescription(product.getDescription());
            productDto.setImage(product.getImage());
            productDto.setTitle(product.getTitle());

            productDtos.add(productDto);
        }
        return productDtos;


    }

    // need to solve this
    @Override
    public List<String> getAllCategories() {
        List<Category> categories = categoryRepository.findAll().stream()
                .collect(Collectors.toList());

        List<Product> products = productRepository.findAllByCategoryIn(categories);

        List<String> titles = new ArrayList<>();

        for (Product p: products) {
            titles.add(p.getTitle());
        }

        return titles;
    }

}