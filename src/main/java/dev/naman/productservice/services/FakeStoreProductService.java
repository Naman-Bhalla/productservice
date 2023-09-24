package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductDto;
import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoryProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    /* //to communicate with third party API--fakestoreApi()
        private RestTemplateBuilder restTemplateBuilder ;
        private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
        private String createProductRequestUrl = "https://fakestoreapi.com/products";

        private String deleteProductRequestUrl = "https://fakestoreapi.com/products/{id}";

        private String updateProductRequestUrl = "https://fakestoreapi.com/products/{id}";

        //inject templateBuilder bean
        public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
            this.restTemplateBuilder = restTemplateBuilder;
        }
        public GenericProductDto getProductById(Long id){
            //return new Product();
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);
            FakeStoreProductDto fakeStoreProductDto = response.getBody(); //retrieving the response

            GenericProductDto genericProductDto = new GenericProductDto();
            genericProductDto.setId(fakeStoreProductDto.getId());
            genericProductDto.setDescription(fakeStoreProductDto.getDescription());
            genericProductDto.setImage(fakeStoreProductDto.getImage());
            genericProductDto.setCategory(fakeStoreProductDto.getCategory());
            genericProductDto.setPrice(fakeStoreProductDto.getPrice());

            return genericProductDto;
        }

        public void deleteProductById(Long id){
            RestTemplate restTemplate = restTemplateBuilder.build();
            restTemplate.delete(deleteProductRequestUrl, id);
        }

        public void updateProductById(Long id){
            //RestTemplate restTemplate = restTemplateBuilder.build();
            //ResponseEntity<FakeStoreProductDto> response = restTemplate.put();
        }*/
    private FakeStoryProductServiceClient fakeStoryProductServiceClient;

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

    public FakeStoreProductService(FakeStoryProductServiceClient fakeStoryProductServiceClient) {
        this.fakeStoryProductServiceClient = fakeStoryProductServiceClient;
    }


    @Override
    public GenericProductDto createProduct(Product product) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.createProduct(product));
    }

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {
        // return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.getProductById(id));
        return null;
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
    public void deleteProductById(UUID id) {
        // return convertFakeStoreProductIntoGenericProduct(fakeStoryProductServiceClient.deleteProduct(id));
    }

    public void updateProductById(UUID id, Product updateProduct){
    }
}
