package dev.bhanu.productservice.controllers;

import dev.bhanu.productservice.Exception.NotFoundException;
import dev.bhanu.productservice.dtos.SelfGenericProductDto;
import dev.bhanu.productservice.dtos.ProductDto;
import dev.bhanu.productservice.dtos.ExceptionDto;
import dev.bhanu.productservice.services.DbProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/dbproducts")
public class SelfProductController {
    private DbProductService dbProductService;

    @Autowired
    public SelfProductController(DbProductService dbProductService){
        this.dbProductService = dbProductService;
    }

    @GetMapping("{uuid}")
    public ProductDto getProductByid(@PathVariable("uuid") String uuid) throws NotFoundException {
        return dbProductService.getProductById(uuid);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody SelfGenericProductDto product){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());
        System.out.println(roles);
        return dbProductService.createProduct(product);
    }

    @PutMapping("{id}")
    public ProductDto updateProductById(@PathVariable("id") String id, @RequestBody ProductDto product) throws NotFoundException {
        return dbProductService.updateProductById(id, product);

    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){

        List<ProductDto> productDtos = dbProductService.getAllProduct();
        return new ResponseEntity<>(
                productDtos,
                HttpStatus.OK
        );
    }

    @GetMapping("/category/{name}")
    public List<ProductDto> getAllProductInCategory(@PathVariable("name") String categoryname) throws NotFoundException {
        return dbProductService.getAllProductByCategory(categoryname);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable("id") String id) throws NotFoundException {
        dbProductService.deleteProductById(id);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity(new ExceptionDto(notFoundException.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}
