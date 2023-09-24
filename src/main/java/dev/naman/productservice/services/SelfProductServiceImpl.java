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

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDto getProductById(String id) {
        return productToGenericProductDtoConvertor(productRepository.findById(UUID.fromString(id)).orElseGet(null));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        if(product==null) return null;
        return productToGenericProductDtoConvertor(productRepository.save(productDTOtoProduct(product)));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        List<Product> products =  productRepository.findAll();
        List<GenericProductDto> answer = new ArrayList<>();
        for(Product p: products){
            answer.add(productToGenericProductDtoConvertor(p));
        }
        return answer;
    }

    @Override
    public GenericProductDto deleteProduct(String id) {
        GenericProductDto product = getProductById(id);
        productRepository.deleteById(UUID.fromString(id));
        return product;
    }

    private GenericProductDto productToGenericProductDtoConvertor(Product prod) {
        //Product prod = product.orElseGet(null);
        if(prod==null) return null;
        GenericProductDto productDto= new GenericProductDto();
        productDto.setId(prod.getUuid());
        productDto.setCategory(prod.getCategory().getName());
        productDto.setTitle(prod.getTitle());
        productDto.setPrice(prod.getPrice().getPrice());
        productDto.setImage(prod.getImage());
        return productDto;
    }

    private Product productDTOtoProduct(GenericProductDto prod){
        Product product = new Product();
        if(prod==null) return null;
        product.setUuid(prod.getId());
    //    product.setCategory( prod.getCategory().getName());
        product.setTitle(prod.getTitle());
    //    product.setPrice(prod.getPrice().getPrice());
        product.setImage(prod.getImage());
        return product;
    }
}
