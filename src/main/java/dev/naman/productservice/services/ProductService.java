package dev.naman.productservice.services;

import dev.naman.productservice.dtos.DisplayProductDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("productService")
public interface ProductService {


    // use for LOCAL DATABASE
    // ************************************************

    GenericProductDto createProduct(GenericProductDto genericProductDto);

    List<GenericProductDto> getAllProducts();


    GenericProductDto getProductById(UUID uuid) throws NotFoundException;

    GenericProductDto updateProduct(UUID uuid, GenericProductDto genericProductDto) throws NotFoundException;

    GenericProductDto deleteProduct(UUID id) throws NotFoundException;

    // ****************** END ****************************

    // use for FAKESTORE API
    // ************************************************

//    FakeStoreProductDto createProduct(GenericProductDto genericProductDto);
//
//    List<FakeStoreProductDto> getAllProducts() throws NotFoundException;
//
//    FakeStoreProductDto getProductById(String id) throws NotFoundException;
//
//    FakeStoreProductDto updateProduct(String id, FakeStoreProductDto fakeStoreProductDto) throws NotFoundException;
//
//    FakeStoreProductDto deleteProduct(String id) throws NotFoundException;

    // ************************************************
}
