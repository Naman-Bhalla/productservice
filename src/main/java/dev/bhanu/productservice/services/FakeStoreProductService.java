package dev.bhanu.productservice.services;

import dev.bhanu.productservice.Exception.NotFoundException;
import dev.bhanu.productservice.dtos.FakeStoreProductDto;
import dev.bhanu.productservice.dtos.GenericProductDto;
import dev.bhanu.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

//    @Value("${fakestore.api.url}")
    private String fakeStoreBaseURL;

//    @Value("${fakestore.product.url")
    private String fakeStoreProductBaseURL;

    private String fakeStoreProductURL;

    private String fakeStoreSpecificProductURL;

    @Autowired
    public FakeStoreProductService(@Value("${fakestore.api.url}") String fakeStoreBaseURL, @Value("${fakestore.product.api.url}") String fakeStoreProductBaseURL,  RestTemplateBuilder restTemplateBuilder){
        this.fakeStoreProductURL = fakeStoreBaseURL + fakeStoreProductBaseURL;
        this.fakeStoreSpecificProductURL = fakeStoreBaseURL + fakeStoreProductBaseURL + "/{id}";
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(fakeStoreSpecificProductURL, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null) throw new NotFoundException("Product not found!!");

        GenericProductDto product = new GenericProductDto();

        product.setCategory(fakeStoreProductDto.getCategory());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setId(fakeStoreProductDto.getId());


        return product;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(fakeStoreSpecificProductURL, product, GenericProductDto.class);
        return response.getBody();
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto data){
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        FakeStoreProductDto fakeStoreproduct = new FakeStoreProductDto();

        fakeStoreproduct.setCategory(data.getCategory());
        fakeStoreproduct.setTitle(data.getTitle());
        fakeStoreproduct.setDescription(data.getDescription());
        fakeStoreproduct.setImage(data.getImage());
        fakeStoreproduct.setPrice(data.getPrice());
        fakeStoreproduct.setId(data.getId());


        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStoreproduct, headers);


        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.exchange(fakeStoreSpecificProductURL, HttpMethod.PUT, requestEntity, FakeStoreProductDto.class, id);


        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        GenericProductDto product = new GenericProductDto();

        product.setCategory(fakeStoreProductDto.getCategory());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setId(fakeStoreProductDto.getId());


        return product;
    }

    @Override
    public List<GenericProductDto> getAllProduct(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(fakeStoreProductURL, FakeStoreProductDto[].class);

        ArrayList<GenericProductDto> product = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: Arrays.stream(response.getBody()).toList()){
            GenericProductDto genericProductDto = new GenericProductDto();
            genericProductDto.setCategory(fakeStoreProductDto.getCategory());
            genericProductDto.setTitle(fakeStoreProductDto.getTitle());
            genericProductDto.setDescription(fakeStoreProductDto.getDescription());
            genericProductDto.setImage(fakeStoreProductDto.getImage());
            genericProductDto.setPrice(fakeStoreProductDto.getPrice());
            genericProductDto.setId(fakeStoreProductDto.getId());

            product.add(genericProductDto);
        }

        return product;

    }
}
