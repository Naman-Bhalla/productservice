package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.services.ProductServiceDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    //    @Autowired
    // field injection
    private final ProductServiceDB productServiceDB;

    public ProductController(ProductServiceDB productServiceDB) {
        this.productServiceDB = productServiceDB;
    }
    // ....;
    // ...;





    // setter injection
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    // GET /products {}
    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productServiceDB.getAllProducts();
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return productServiceDB.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(
                productServiceDB.deleteProductById(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
//        System.out.println(product.name);
        return productServiceDB.createProduct(product);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto, @PathVariable("id") UUID id) {
        return productServiceDB.updateProductById(genericProductDto,id);
    }

    @GetMapping("/categories/{uuid}")
    public List<ProductDto> getCategoryById(@PathVariable("uuid") String uuid){
        return productServiceDB.getCategoryById(uuid);
    }

    // get all categories
    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return productServiceDB.getAllCategories();
    }
}
