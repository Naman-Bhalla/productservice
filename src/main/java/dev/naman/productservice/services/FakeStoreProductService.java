package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductDto;
import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }
    public List<GenericProductDto>  getAllProducts() throws NotFoundException{
        List<GenericProductDto> list = new ArrayList<>();
        List<FakeStoreProductDto> fakeList = fakeStoreProductServiceClient.getAllProducts();
        for(FakeStoreProductDto fakeStoreProductDto: fakeList){
            list.add(convertFakeStoreToGenericProductDto(fakeStoreProductDto));
        }
        return list;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException{
        //return new Product();
        return convertFakeStoreToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
    }
    @Override
    public GenericProductDto getProductById(UUID uuid) throws NotFoundException{
        return null;
    }
    @Override
    public GenericProductDto deleteProductById(Long id) throws NotFoundException{
        return convertFakeStoreToGenericProductDto(fakeStoreProductServiceClient.deleteProductById(id));
    }
    @Override
    public GenericProductDto deleteProductById(UUID id) throws NotFoundException{
        return null;
    }
    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        return convertFakeStoreToGenericProductDto(fakeStoreProductServiceClient.createProduct(product));
    }
    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) throws NotFoundException {
        return convertFakeStoreToGenericProductDto(fakeStoreProductServiceClient.updateProductById(id,product));
    }
    @Override
    public GenericProductDto updateProductById(UUID id, GenericProductDto product) throws NotFoundException{
        return null;
    }
    private GenericProductDto convertFakeStoreToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
//        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }
}