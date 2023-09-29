package dev.bhanu.productservice.services;

import dev.bhanu.productservice.Exception.NotFoundException;
import dev.bhanu.productservice.Repository.CategoryRepository;
import dev.bhanu.productservice.Repository.ProductRepository;
import dev.bhanu.productservice.dtos.SelfGenericProductDto;
import dev.bhanu.productservice.dtos.ProductDto;
import dev.bhanu.productservice.models.Category;
import dev.bhanu.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DbProductService implements SelfProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    DbProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public ProductDto createProduct(SelfGenericProductDto product) {
        Product product1 = new Product();

        Optional<Category> optionalCategory = categoryRepository.getCategoryByName(product.getCategory());

        if(!optionalCategory.isEmpty()){
            Category category = optionalCategory.get();
            product1.setCategory(category);
        }else{
            Category category = new Category();
            category.setName(product.getCategory());
            product1.setCategory(category);
        }


        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setImage(product.getImage());
//        product1.setCategory(category);


        Product savedProduct = productRepository.save(product1);
        return convertToDBProductDTO(savedProduct);
    }

    @Override
    public ProductDto getProductById(String uuid) throws NotFoundException {
        return fetchProductByID(uuid);
    }

    @Transactional
    public ProductDto fetchProductByID(String uuid) throws NotFoundException {
        Optional<Product> product = productRepository.findById(UUID.fromString(uuid));
        if(product.isEmpty()) throw new NotFoundException("product not found");
        Product product1 = product.get();
        Category category = product1.getCategory();
        product1.setCategory(category);
        return convertToDBProductDTO(product1);
    }

    @Override
    public ProductDto updateProductById(String id, ProductDto product) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()) throw new NotFoundException("product not found");

        Product product1 = optionalProduct.get();

        product1.setTitle(product.getTitle());
        product1.setPrice(product.getPrice());
        product1.setImage(product.getImage());

        Product savedProduct = productRepository.save(product1);
        return convertToDBProductDTO(savedProduct);

    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product1: products){
            productDtos.add(convertToDBProductDTO(product1));
        }


        return productDtos;
    }

    @Override
    public List<ProductDto> getAllProductByCategory(String name) throws NotFoundException{
        Optional<Category> optionalCategory = categoryRepository.getCategoryByName(name);
        if(optionalCategory.isEmpty()) new NotFoundException("category not found");

        Category category = optionalCategory.get();

        List<Product> products = productRepository.findProductByCategory(category);

        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product1: products){
            productDtos.add(convertToDBProductDTO(product1));
        }


        return productDtos;


    }

    @Override
    public void deleteProductById(String id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()) throw new NotFoundException("product not found");
        productRepository.delete(optionalProduct.get());
    }


    ProductDto convertToDBProductDTO(Product product1){
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product1.getTitle());
        productDto.setUuid(product1.getId());
        productDto.setPrice(product1.getPrice());
        productDto.setImage(product1.getImage());
        productDto.setCategory(product1.getCategory().getName());
        return productDto;
    }

}
