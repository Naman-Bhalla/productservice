package dev.naman.productservice.services;

import dev.naman.productservice.dtos.*;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.SelfProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelfStoreProductService implements SelfProductService {

    private final SelfProductRepository selfProductRepository;


    public SelfStoreProductService(SelfProductRepository selfProductRepository) {
        this.selfProductRepository = selfProductRepository;
    }

    private CreateProductResponseDto convertCreateProductRequestDtoToResponseDto(CreateProductRequestDto request) {
        Product newProduct = new Product();
        newProduct.setImage(request.getImage());
        newProduct.setCategory(request.getCategory());
        newProduct.setDescription(request.getDescription());
        newProduct.setPrice(request.getPrice());
        newProduct.setTitle(request.getTitle());

        Product createdProduct = selfProductRepository.save(newProduct);
        CreateProductResponseDto response = new CreateProductResponseDto();
        response.setId(createdProduct.getId());
        response.setDescription(createdProduct.getDescription());
        response.setCategory(createdProduct.getCategory());
        response.setPrice(createdProduct.getPrice());
        response.setTitle(createdProduct.getTitle());
        response.setImage(createdProduct.getImage());
        return response;
    }

    @Override
    public CreateProductResponseDto createProduct(CreateProductRequestDto request) {

        return convertCreateProductRequestDtoToResponseDto(request);

    }

    @Override
    public GetProductResponseDto getProductById(Long id) {

        Product product = selfProductRepository.getProductsById(id);
        if (product == null) {
            return null;
        }
        GetProductResponseDto response = new GetProductResponseDto();

        response.setId(product.getId());
        response.setDescription(product.getDescription());
        response.setCategory(product.getCategory());
        response.setPrice(product.getPrice());
        response.setImage(product.getImage());
        response.setTitle(product.getTitle());
        return response;
    }

    @Override
    public GetAllProductResponseDto getAllProducts() {

        GetAllProductResponseDto allSelfProductDtos = new GetAllProductResponseDto();
        List<GetProductResponseDto> allProductResponseDto = new ArrayList<>();
        List<Product> products = selfProductRepository.getAllProducts();
        return getGetAllProductResponseDto(products, allSelfProductDtos, allProductResponseDto);
    }

    @Override
    public GetProductResponseDto updateProductById(CreateProductRequestDto request, Long id) {
        Product product = selfProductRepository.getProductsById(id);
        if (product == null) {
            return null;
        }
        GetProductResponseDto response = new GetProductResponseDto();
        response.setId(id);
        response.setDescription(product.getDescription());
        response.setCategory(product.getCategory());
        response.setPrice(product.getPrice());
        response.setImage(product.getImage());
        response.setTitle(product.getTitle());
        return response;
    }

    @Override
    public DeleteProductResponseDto deleteProductById(Long id) {
        Product product = selfProductRepository.getProductsById(id);
        if (product == null) {
            return null;
        }
        selfProductRepository.delete(product);
        DeleteProductResponseDto response = new DeleteProductResponseDto();
        response.setId(id);
        response.setDescription(product.getDescription());
        response.setCategory(product.getCategory());
        response.setPrice(product.getPrice());
        response.setImage(product.getImage());
        response.setTitle(product.getTitle());
        return response;

    }

    @Override
    public GetAllProductResponseDto getProductsByCategory(String category) {
        List<Product> productDetailsByCategory = selfProductRepository.getProductsByCategory(category);
        if (productDetailsByCategory.isEmpty()) {
            return null;
        }

        GetAllProductResponseDto allSelfProductDtos = new GetAllProductResponseDto();
        List<GetProductResponseDto> allProductResponseDto = new ArrayList<>();
        return getGetAllProductResponseDto(productDetailsByCategory, allSelfProductDtos, allProductResponseDto);
    }

    private GetAllProductResponseDto getGetAllProductResponseDto(List<Product> productDetailsByCategory, GetAllProductResponseDto allSelfProductDtos, List<GetProductResponseDto> allProductResponseDto) {
        for (Product product : productDetailsByCategory) {
            GetProductResponseDto response = new GetProductResponseDto();
            response.setId(product.getId());
            response.setDescription(product.getDescription());
            response.setCategory(product.getCategory());
            response.setPrice(product.getPrice());
            response.setImage(product.getImage());
            response.setTitle(product.getTitle());


            allProductResponseDto.add(response);
        }
        allSelfProductDtos.setAllProductResponseDto(allProductResponseDto);
        return allSelfProductDtos;
    }

    @Override
    public List<CategoryTypeDto> getAllCategories() {
        List<CategoryTypeDto> allCategoryResponseDtos = new ArrayList<>();
        List<String> category = selfProductRepository.getAllByCategory();
        for (String category1 : category) {
            CategoryTypeDto res = new CategoryTypeDto();
            res.setCategory(category1);

            allCategoryResponseDtos.add(res);
        }
        return allCategoryResponseDtos;
    }

}
