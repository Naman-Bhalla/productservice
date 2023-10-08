package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.*;
import dev.naman.productservice.services.SelfStoreProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final SelfStoreProductService selfStoreProductService;

    public ProductController(SelfStoreProductService selfStoreProductService) {
        this.selfStoreProductService = selfStoreProductService;
    }

    @PostMapping("/create")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto product) {
        return selfStoreProductService.createProduct(product);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetProductResponseDto> getProductById(@PathVariable("id") Long id) {
        GetProductResponseDto response = selfStoreProductService.getProductById(id);
        if (response == null) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    public ResponseEntity<GetAllProductResponseDto> getAllProducts() {
        GetAllProductResponseDto response = selfStoreProductService.getAllProducts();
        if (response.getAllProductResponseDto().isEmpty()) {
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );

    }

    @PutMapping("{id}")
    public ResponseEntity<GetProductResponseDto> updateProductById(@RequestBody CreateProductRequestDto product, @PathVariable("id") Long id) {
        GetProductResponseDto response = selfStoreProductService.updateProductById(product, id);

        if (response == null) {
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeleteProductResponseDto> deleteProductById(@PathVariable("id") Long id) {
        DeleteProductResponseDto response = selfStoreProductService.deleteProductById(id);
        if (response == null) {
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );


    }

    @GetMapping("/category/{categoryType}")
    public ResponseEntity<GetAllProductResponseDto> getCategoryById(@PathVariable("categoryType") String category) {
        GetAllProductResponseDto response = selfStoreProductService.getProductsByCategory(category);
        if (response == null) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryTypeDto>> getAllCategories() {
        List<CategoryTypeDto> response = selfStoreProductService.getAllCategories();

        if (response.isEmpty()) {
            return new ResponseEntity<>(
                    response,
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

}
