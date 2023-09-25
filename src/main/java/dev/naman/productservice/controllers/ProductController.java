package dev.naman.productservice.controller;

import dev.naman.productservice.dtos.ExceptionDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.services.ProductService;
import dev.naman.productservice.services.SelfProductServiceImpl;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
//    @Autowired
    //field injection. Not recommended.

    //Dependency Injection. Here, ProductService which is a dependency is being injected
    private ProductService productService;
    private SelfProductServiceImpl selfProductService;
    //constructor injection. This is recommended practice.
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService, SelfProductServiceImpl selfProductService){ //if there are more than one implementation for the ProductService interface,
        //Spring wouldn't know which one to use. @Qualifier tells Spring which one to use!
        this.productService = productService;
        this.selfProductService = selfProductService;
    }
    // setter injection. not recommended
//    @Autowired
//    public void setProductService(ProductService productService){
//        this.productService = productService;
//    }
    @GetMapping
    public List<GenericProductDto> getAllProducts() throws NotFoundException {
        return selfProductService.getAllProducts();
    }
    // localhost:8080/products/123
    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") String id) throws NotFoundException{
        return selfProductService.getProductById(UUID.fromString(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") String id) throws NotFoundException {
        ResponseEntity<GenericProductDto> responseEntity =
                new ResponseEntity<>(selfProductService.deleteProductById(UUID.fromString(id)), HttpStatus.NOT_FOUND);
        return responseEntity;
    }
    //@RequestBody converts whatever is in the request body to GenericProductDto
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return selfProductService.createProduct(genericProductDto);
    }
    @PutMapping("/{id}")
    public GenericProductDto updateProductById(
            @PathVariable("id") String id, @RequestBody GenericProductDto genericProduct) throws NotFoundException{
        return selfProductService.updateProductById(UUID.fromString(id),genericProduct);
    }
}
