package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.ExceptionDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.services.ProductService;
import dev.naman.productservice.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    // @Autowired
    // field injection
    private ProductServiceImpl productServiceImpl;

    // tells spring which implementation class to inject by mentioning
    // @Qualalifier(className)
    // constructor injection
    // @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {

        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
        return productServiceImpl.createProduct(product);
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("/{id}") // here id is a variable which will change based on user input
    public GenericProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return productServiceImpl.getProductById(id);

    }

    @GetMapping()
    public List<GenericProductDto> getAllProducts() {

        return productServiceImpl.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") UUID id) {

        return new ResponseEntity<>(
                productServiceImpl.deleteProductById(id),
                HttpStatus.OK // status code which we can send manually
        );
    }

    @PutMapping("/{id}")
    public GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto, @PathVariable("id") UUID id) {

        return productServiceImpl.updatePrductById(genericProductDto,id);
    }

    @GetMapping("/categories/{uuid}")
    public List<ProductDto> getCategoryById(@PathVariable("uuid") String uuid){
        return productServiceImpl.getCategoryById(uuid);
    }

    // get all categories
    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return productServiceImpl.getAllCategories();
    }
}
