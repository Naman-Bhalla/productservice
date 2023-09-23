package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        return convertGenericProductDtoToProduct(productRepository.findById(id).get());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {

        Product product = new Product();
        Category category = new Category();
        Price price= new Price();
        price.setCurrency("INR");
        price.setPrice(genericProductDto.getPrice());
        category.setName(genericProductDto.getCategory());
        product.setTitle(genericProductDto.getTitle());
        product.setDescription(genericProductDto.getDescription());
        product.setImage(genericProductDto.getImage());
        product.setCategory(category);
        product.setPrice(price);
        Product in = productRepository.save(product);
        return convertGenericProductDtoToProduct(in);
    }

    private GenericProductDto convertGenericProductDtoToProduct(Product product) {
        GenericProductDto genericProductDto= new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream().map(product -> convertGenericProductDtoToProduct(product))
                .collect(Collectors.toList());
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            return null;
        }
        productRepository.deleteById(id);
        return convertGenericProductDtoToProduct(product.get());
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto genericProductDto) {
        Product product = new Product();
        Category category = new Category();
        Price price= new Price();
        price.setCurrency("INR");
        price.setPrice(genericProductDto.getPrice());
        category.setName(genericProductDto.getCategory());
        product.setId(genericProductDto.getId());
        product.setTitle(genericProductDto.getTitle());
        product.setDescription(genericProductDto.getDescription());
        product.setImage(genericProductDto.getImage());
        product.setCategory(category);
        product.setPrice(price);
        Product in = productRepository.save(product);
        return convertGenericProductDtoToProduct(in);
    }
    }

