package dev.daliya.productService.services;

import dev.daliya.productService.dtos.GenericProductDto;
import dev.daliya.productService.exeptions.NotFoundException;
import dev.daliya.productService.thirdPartyClients.productService.fakeStore.FakeStoreProductDto;
import dev.daliya.productService.thirdPartyClients.productService.fakeStore.FakeStoreProductServiceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private final FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.getProductById(id);
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }

        GenericProductDto product = convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
        return product;
    }

    private static GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        var response = fakeStoreProductServiceClient.getAllProducts();
        List<GenericProductDto> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : response) {
            GenericProductDto product = convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
            products.add(product);
        }
        return products;
    }

    @Override
    public GenericProductDto deleteProductById(UUID id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.deleteProductById(id);
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }


    @Override
    public GenericProductDto updateProductById(GenericProductDto product, UUID id) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.updateProductById(product, id);
        if (fakeStoreProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }


}
