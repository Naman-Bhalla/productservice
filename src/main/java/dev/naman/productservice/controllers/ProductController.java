package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.services.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@NoArgsConstructor
public class ProductController {
//    @Autowired
    // field injection

    @Autowired
    private ProductService productService;
    // ....;
    // ...;



    // constructor injection
//    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
//

    // setter injection
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    // GET /products {}
    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts() throws NotFoundException {
        List<GenericProductDto> productDtoList = productService.getAllProducts();
        if (productDtoList.isEmpty()) {
            return new ResponseEntity<>(
                    productDtoList,
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) throws NotFoundException {
        return productService.updateProduct(id, genericProductDto);
    }
}
