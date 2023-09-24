package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.services.SevenApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class SevenApiController {
    private SevenApiService sevenApiService;
    private ProductDto productdto;

    public SevenApiController(SevenApiService sevenApiService) {
        this.sevenApiService = sevenApiService;
    }
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return sevenApiService.getAllProducts();
    }
    @GetMapping("/id/")
    public ProductDto findProductById(@PathVariable("uuid") String uuid) {
        Product product = sevenApiService.findProductbyId(uuid);
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setTitle(product.getTitle());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return sevenApiService.getAllCategories();
    }
    @GetMapping("/categories/{uuid}")
    public List<Product> getProductsByCategory(@PathVariable("uuid") String uuid) {
        return sevenApiService.getProductsByCategory(uuid);
    }
    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return sevenApiService.createProduct(product);
    }
    @PutMapping("/{uuid}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("uuid") String uuid) {
        return sevenApiService.updateProduct(product, uuid);
    }
    @DeleteMapping("/{uuid}")
    public String deleteProduct(@PathVariable("uuid") String uuid) {
        sevenApiService.deleteProduct(uuid);
        return "Deleted Successfully";
    }
}
