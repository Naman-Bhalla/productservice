//package dev.naman.productservice.services;
//
//import dev.naman.productservice.dtos.GenericProductDto;
//import dev.naman.productservice.exceptions.NotFoundException;
//import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductDto;
//import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductServiceClient;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//
//@Repository("fakeStoreProductServiceImpl")
//public class FakeStoreProductServiceImpl implements ProductService {
//
//    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
//
//    @Override
//    public String getQualifierValue() {
//        return "fakeStoreProductServiceImpl";
//    }
//
//    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto) {
//
//        GenericProductDto product = new GenericProductDto();
//        product.setId(fakeStoreProductDto.getId());
//        product.setImage(fakeStoreProductDto.getImage());
//        product.setDescription(fakeStoreProductDto.getDescription());
//        product.setTitle(fakeStoreProductDto.getTitle());
//        product.setPrice(fakeStoreProductDto.getPrice());
//        product.setCategory(fakeStoreProductDto.getCategory());
//
//        return product;
//    }
//
//    public FakeStoreProductServiceImpl(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
//        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
//    }
//
//
//    @Override
//    public GenericProductDto createProduct(GenericProductDto product) {
//        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.createProduct(product));
//    }
//
//    @Override
//    public GenericProductDto getProductById(Long id) throws NotFoundException {
//
//        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.getProductById(id));
//    }
//
//    @Override
//    public GenericProductDto updateProduct(Long id, FakeStoreProductDto fakeStoreProductDto) throws NotFoundException {
//        return new GenericProductDto();
//    }
//
//    @Override
//    public List<GenericProductDto> getAllProducts() {
//        List<GenericProductDto> genericProductDtos = new ArrayList<>();
//
//        for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductServiceClient.getAllProducts()) {
//            genericProductDtos.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
//        }
//        return genericProductDtos;
//    }
//
//    @Override
//    public GenericProductDto deleteProduct(Long id) {
//        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.deleteProduct(id));
//    }
//}
