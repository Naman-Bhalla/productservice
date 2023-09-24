package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public GenericProductDto getProductById(String id) {
        Optional<Product> productOptional = productRepository.findById(UUID.fromString(id));
        if (productOptional.isEmpty()) {
            return null;
        }
        Product product = productOptional.get();
        //Setting Generic product values and returning it to user
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setImage(product.getImage());

        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        Category category = new Category();
        category.setName(category.getName());
        genericProductDto.setCategory(category.getName());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setImage(product.getImage());
        productRepository.save(product);

        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        List<Product>  products = new ArrayList<>();
        products = productRepository.findAll();

        for (Product product : products) {
            GenericProductDto genericProductDto = new GenericProductDto();
            genericProductDto.setPrice(product.getPrice().getPrice());
            genericProductDto.setCategory(product.getCategory().getName());
            genericProductDto.setDescription(product.getDescription());
            genericProductDto.setTitle(product.getTitle());
            genericProductDto.setImage(product.getImage());
            genericProductDtos.add(genericProductDto);
        }
        return genericProductDtos;

    }

    @Override
    public void deleteProductById(UUID id) {
        productRepository.deleteById(id);

    }

    public void updateProductById(UUID id, Product updateProduct){
        Product product = productRepository.findByUuid(id);
        product.setImage(updateProduct.getImage());
        product.setTitle(updateProduct.getTitle());
        product.setDescription(updateProduct.getDescription());
        product.setPrice(updateProduct.getPrice());
        product.setCategory(updateProduct.getCategory());

        productRepository.save(product);
    }
}
