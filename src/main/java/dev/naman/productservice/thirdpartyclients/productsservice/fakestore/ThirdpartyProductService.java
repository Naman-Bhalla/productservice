package dev.naman.productservice.thirdpartyclients.productsservice.fakestore;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ThirdpartyProductService {
    List<FakeStoreProductDto> getAllProducts();
    FakeStoreProductDto getProductById(Long id) throws NotFoundException;
    FakeStoreProductDto deleteProductById(Long id);
    FakeStoreProductDto createProduct(GenericProductDto product);
    FakeStoreProductDto updateProductById(Long id, GenericProductDto product);
}