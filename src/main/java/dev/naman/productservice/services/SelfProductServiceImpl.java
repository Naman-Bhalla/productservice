package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Primary
@Repository("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    public SelfProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public GenericProductDto getProductById(UUID uuid) {
        Product product = productRepository.findById(uuid).get();

        return GetGenericProductDto(product);
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        Product product1 = new Product();


        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        Category category = new Category();
        category.setName(product.getCategory());
        product1.setCategory(category);
        Price price = new Price();
        price.setPrice(product.getPrice());
        product1.setPrice(price);
        product1.setImage(product.getImage());

        productRepository.save(product1);

        return GetGenericProductDto(product1);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        List<Product> products = productRepository.findAll();

        for(Product product : products){
            genericProductDtos.add(GetGenericProductDto(product));
        }

        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProduct(UUID uuid) {

        productRepository.deleteById(uuid);
        return new GenericProductDto();
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }

    private GenericProductDto GetGenericProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setUuid(product.getUuid());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setImage(product.getImage());

        return genericProductDto;
    }
}
