package dev.naman.productservice.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    // @Autowired
    // field injection
    private ProductService productService;
    // ....;
    // ...;

    // constructor injection
    // @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //

    // setter injection
    // @Autowired
    // public void setProductService(ProductService productService) {
    // this.productService = productService;
    // }

    // GET /products {}
    @GetMapping
    // public List<GenericProductDto> getAllProducts() {
    public List<GenericProductDto> getAllProducts() {

        return productService.getAllProducts();
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK);
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        // System.out.println(product.name);
        return productService.createProduct(product);
    }

    @GetMapping("fromCategories")
    public List<GenericProductDto> findProductsInCategory(@RequestBody List<String> uuids) {
        return productService.getProductsInCategory(uuids);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") String id, @RequestBody GenericProductDto product) {
        return productService.updateProductById(id, product);
    }
}
