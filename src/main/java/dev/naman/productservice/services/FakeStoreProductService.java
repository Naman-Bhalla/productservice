package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.security.JwtObject;
import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductDto;
import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoryProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private FakeStoryProductServiceClient fakeStoryProductServiceClient;
    private RedisTemplate<String, Object> redisTemplate;
    // String -> Data Type of Key
    // Object -> Data Type of Value


    @Autowired
    public FakeStoreProductService(FakeStoryProductServiceClient fakeStoryProductServiceClient,
                                   RedisTemplate<String, Object> redisTemplate) {
        this.fakeStoryProductServiceClient = fakeStoryProductServiceClient;
        this.redisTemplate = redisTemplate;
    }

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto) {

        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }

//    public FakeStoreProductService(FakeStoryProductServiceClient fakeStoryProductServiceClient) {
//        this.fakeStoryProductServiceClient = fakeStoryProductServiceClient;
//    }


    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.createProduct(product));
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        // KEY: 1_FAKESTORE
        System.out.println("In product service");
        // check if this product id already exists in my cache
        GenericProductDto genericProductDto = (GenericProductDto) redisTemplate.opsForHash().get("PRODUCTS", id);

        if (genericProductDto != null) {
            System.out.println("Fetched from Cache");
            return genericProductDto;
        }

         GenericProductDto genericProductDto1 = convertFakeStoreProductIntoGenericProduct(
                 fakeStoryProductServiceClient.getProductById(id));
        System.out.println("Fetching from API");
         redisTemplate.opsForHash().put("PRODUCTS", id, genericProductDto1);
        // if yes:
        //    return from cache
        // else:
        //    make an api call to fakestore
        //    save details in cache
        //    return
        return genericProductDto1;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProductDto: fakeStoryProductServiceClient.getAllProducts()) {
            genericProductDtos.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.deleteProduct(id));
    }
}
