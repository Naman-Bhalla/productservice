package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.ProductRepository;
import dev.naman.productservice.repositories.SelfProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private SelfProductRepository selfProductRepository;

    public SelfProductServiceImpl(SelfProductRepository selfProductRepository) {
        this.selfProductRepository = selfProductRepository;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        Optional<Product> product =  selfProductRepository.findById(id);
        if(product.isEmpty()){
            return new GenericProductDto();
        }
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(product.get().getTitle());
        genericProductDto.setPrice(product.get().getPrice().getPrice());
        genericProductDto.setDescription(product.get().getDescription());
        genericProductDto.setImage(product.get().getImage());
        genericProductDto.setCategory(product.get().getCategory().getName());
        genericProductDto.setId(id);

        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
     Product prod =  selfProductRepository.save(product);
       GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(prod.getTitle());
        genericProductDto.setPrice(prod.getPrice().getPrice());
        genericProductDto.setDescription(prod.getDescription());
        genericProductDto.setImage(prod.getImage());
        genericProductDto.setCategory(prod.getCategory().getName());
        genericProductDto.setId(product.getId());
        return genericProductDto;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
    List<Product> products = selfProductRepository.findAll();
            List<GenericProductDto> genericProductDtos = new ArrayList<>();
            for(Product product: products){
                GenericProductDto genericProductDto = new GenericProductDto();
                genericProductDto.setTitle(product.getTitle());
                genericProductDto.setPrice(product.getPrice().getPrice());
                genericProductDto.setDescription(product.getDescription());
                genericProductDto.setImage(product.getImage());
                genericProductDto.setCategory(product.getCategory().getName());
                genericProductDto.setId(1L);
                genericProductDtos.add(genericProductDto);
            }
            return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
         selfProductRepository.deleteById(id);
        // process is same as getbyID
    return  null;
    }
}
