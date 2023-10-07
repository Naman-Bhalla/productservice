package dev.naman.productservice.services;

import dev.naman.productservice.dtos.DisplayProductDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("productService")
public interface ProductService {

    String getQualifierValue();

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
//
//    GenericProductDto createProduct(GenericProductDto genericProductDto);
//
//    List<GenericProductDto> getAllProducts() throws NotFoundException;
//
//    GenericProductDto getProductById(Long id) throws NotFoundException;
//
//    GenericProductDto updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto) throws NotFoundException;
//
//    GenericProductDto deleteProduct(Long id) throws NotFoundException;

    // ************************************************
}
