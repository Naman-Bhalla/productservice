package dev.naman.productservice.thirdpartyclients.productsservice.fakestore;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/***
 * Wrapper over FakeStore API
 */
@Controller
public class FakeStoreProductServiceClient implements ThirdpartyProductService {
    private final RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrlAll = "https://fakestoreapi.com/products";
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String deleteProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String updateProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public List<FakeStoreProductDto>  getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(getProductRequestUrlAll,FakeStoreProductDto[].class);
        return Arrays.stream(Objects.requireNonNull(response.getBody())).toList();
    }
    @Override
    public FakeStoreProductDto getProductById(Long id) throws NotFoundException{
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id "+ id + " doesn't exist");
        }
        return response.getBody();
    }
    @Override
    public FakeStoreProductDto deleteProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.exchange(deleteProductRequestUrl,
                        HttpMethod.DELETE,null,FakeStoreProductDto.class,id);
        return response.getBody();
    }
    @Override
    public FakeStoreProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.postForEntity(createProductRequestUrl, product, FakeStoreProductDto.class);
        return response.getBody();
    }
    public FakeStoreProductDto updateProductById(Long id, GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product);
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.exchange(updateProductRequestUrl,HttpMethod.PUT,requestEntity,FakeStoreProductDto.class,id);
        return response.getBody();
    }
}
