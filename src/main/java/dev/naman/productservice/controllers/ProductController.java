package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.ExceptionDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.Request;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.security.JwtObject;
import dev.naman.productservice.security.TokenValidator;
import dev.naman.productservice.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
//    @Autowired
    // field injection
    private ProductService productService;
    private TokenValidator tokenValidator;
    // ....;
    // ...;



    // constructor injection
//    @Autowired
    public ProductController(ProductService productService, TokenValidator tokenValidator) {
        this.productService = productService;
        this.tokenValidator = tokenValidator;
    }
//

    // setter injection
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    // GET /products {}
    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts() {
        List<GenericProductDto> productDtos = productService.getAllProducts();
        if (productDtos.isEmpty()) {
            return new ResponseEntity<>(
                    productDtos,
                    HttpStatus.NOT_FOUND
            );
        }

        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for (GenericProductDto gpd: productDtos) {
            genericProductDtos.add(gpd);
        };

//        genericProductDtos.remove(genericProductDtos.get(0));

        return new ResponseEntity<>(genericProductDtos, HttpStatus.OK);

//        productDtos.get(0).setId(1001L);
//
//        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @PathVariable("id") Long id) throws NotFoundException {
        System.out.println(authToken);
        Optional<JwtObject> authTokenObjOptional;
        JwtObject authTokenObj = null;

        if (authToken != null) {
            authTokenObjOptional = tokenValidator.validateToken(authToken);
            if (authTokenObjOptional.isEmpty()) {
                // ignore
            }

            authTokenObj = authTokenObjOptional.get();
        }

        GenericProductDto productDto = productService.getProductById(id, authTokenObj.getUserId());
        if (productDto == null) {
            throw new NotFoundException("Product Doesn't Exist");
        }

//        GenericProductDto genericProductDto = new GenericProductDto();
//        genericProductDto.setTitle(productDto.getTitle());
        return productDto;

//        Comparator
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
//        System.out.println(product.name);
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public void updateProductById() {

    }
}
