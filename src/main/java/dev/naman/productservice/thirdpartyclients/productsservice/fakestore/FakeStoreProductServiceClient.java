package dev.naman.productservice.thirdpartyclients.productsservice.fakestore;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/***
 * Wrapper over FakeStore API
 */

@Service("fakeStoreProductServiceClient")
public class FakeStoreProductServiceClient {

    private final RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;


    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductsApiPath;

    private final String specificProductRequestUrl;
    private final String productRequestsBaseUrl;


    private FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}") String fakeStoreApiUrl, @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productRequestsBaseUrl = fakeStoreApiUrl + fakeStoreProductsApiPath;
        this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
    }

    public FakeStoreProductDto createProduct(GenericProductDto product) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestsBaseUrl, product, FakeStoreProductDto.class);
        return response.getBody();
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {

        Optional<FakeStoreProductDto> product = getAllProducts().stream().filter(p -> p.getId().equals(id)).findFirst();
        if (product.isEmpty()) {
            throw new NotFoundException("Product with ID: '" + id + "' not Found. Cannot Display Product.");
        }
        FakeStoreProductDto fakeStoreProductDto = product.get();
        fakeStoreProductDto.setId(product.get().getId());
        fakeStoreProductDto.setTitle(product.get().getTitle());
        fakeStoreProductDto.setDescription(product.get().getDescription());
        fakeStoreProductDto.setPrice(product.get().getPrice());
        fakeStoreProductDto.setImage(product.get().getImage());
        fakeStoreProductDto.setCategory(product.get().getCategory());

        return fakeStoreProductDto;
    }


    public List<FakeStoreProductDto> getAllProducts() {

        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestsBaseUrl, FakeStoreProductDto[].class);
        return Arrays.asList(response.getBody());
    }


    public FakeStoreProductDto deleteProductById(Long id) throws NotFoundException {

        RestTemplate restTemplate = restTemplateBuilder.build();

        Optional<FakeStoreProductDto> productResponse = getAllProducts().stream().filter(p -> p.getId().equals(id)).findFirst();
        if (productResponse.isEmpty()) {
            throw new NotFoundException("Product with ID: '" + id + "' not Found. Cannot Delete.");
        }
        String deleteUrl = specificProductRequestUrl.replace("{id}", id.toString());
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                deleteUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        FakeStoreProductDto fakeStoreProductDto = productResponse.get();
        fakeStoreProductDto.setId(productResponse.get().getId());
        fakeStoreProductDto.setTitle(productResponse.get().getTitle());
        fakeStoreProductDto.setDescription(productResponse.get().getDescription());
        fakeStoreProductDto.setPrice(productResponse.get().getPrice());
        fakeStoreProductDto.setImage(productResponse.get().getImage());
        fakeStoreProductDto.setCategory(productResponse.get().getCategory());


        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("Product deleted successfully");
            return fakeStoreProductDto;
        }
        return null;
    }


    public FakeStoreProductDto updateProductById(GenericProductDto productRequestBody, Long id) throws NotFoundException {

        Optional<FakeStoreProductDto> optionalProduct = getAllProducts().stream().filter(p -> p.getId().equals(id)).findFirst();
        if (optionalProduct.isEmpty())
            throw new NotFoundException("Product with ID : " + id + " NOT FOUND, Update Failed.");

        return copyProductDtoProperties(productRequestBody);
    }

    // Utility method
    private FakeStoreProductDto copyProductDtoProperties(GenericProductDto genericProductDto) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
//        fakeStoreProductDto.setId(genericProductDto.getId());
        fakeStoreProductDto.setTitle(genericProductDto.getTitle());
        fakeStoreProductDto.setDescription(genericProductDto.getDescription());
        fakeStoreProductDto.setPrice(genericProductDto.getPrice());
        fakeStoreProductDto.setImage(genericProductDto.getImage());
        fakeStoreProductDto.setCategory(genericProductDto.getCategory());
        return fakeStoreProductDto;
    }

}
