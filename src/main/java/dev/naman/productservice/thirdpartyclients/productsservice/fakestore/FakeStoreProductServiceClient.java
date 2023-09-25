package dev.naman.productservice.thirdpartyclients.productsservice.fakestore;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Wrapper over FakeStore API
 */
@Controller
public class FakeStoreProductServiceClient implements ThirdpartyProductService {
    private RestTemplateBuilder restTemplateBuilder;
    /*@Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;
    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductsApiPath;
    private String getProductRequestUrlAll = fakeStoreApiUrl+fakeStoreProductsApiPath;
    private String getProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath + "/{id}";
    private String deleteProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath + "/{id}";
    private String createProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath;
    private String updateProductRequestUrl = fakeStoreApiUrl+fakeStoreProductsApiPath + "/{id}";*/
    private String getProductRequestUrlAll = "https://fakestoreapi.com/products";
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String deleteProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String updateProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    //My implementation of getAllProducts()
    /*
    @Override
    public List<GenericProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List<GenericProductDto>> response =
                restTemplate.exchange(getProductRequestUrlAll, HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<GenericProductDto>>() {});
        return response.getBody();
    }*/
    //Instructor's implementation of getAllProducts()
//    public List<FakeStoreProductDto>  getAllProducts(){
    public List<FakeStoreProductDto>  getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        System.out.println(getProductRequestUrlAll);
        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(getProductRequestUrlAll,FakeStoreProductDto[].class);
        /*List<GenericProductDto> result = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : response.getBody()){
            result.add(convertFakeStoreToGenericProductDto(fakeStoreProductDto));
        }*/
        return Arrays.stream(response.getBody()).toList();
    }
    @Override
    public FakeStoreProductDto getProductById(Long id) throws NotFoundException{
        //return new Product();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id "+ id + " doesn't exist");
        }
        //GenericProductDto product = convertFakeStoreToGenericProductDto(fakeStoreProductDto);
        return response.getBody();
    }
    @Override
    public FakeStoreProductDto deleteProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.exchange(deleteProductRequestUrl,
                        HttpMethod.DELETE,null,FakeStoreProductDto.class,id);
        //ResponseEntity<FakeStoreProductDto> response = restTemplate.delete();
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
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.exchange(updateProductRequestUrl,HttpMethod.PUT,requestEntity,FakeStoreProductDto.class,id);
        return response.getBody();
    }
    /*private GenericProductDto convertFakeStoreToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }*/
}

