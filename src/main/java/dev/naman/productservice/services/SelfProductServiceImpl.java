package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.mapper.ProductMapper;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.ProductRepository;
import dev.naman.productservice.utility.ProductUtility;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public GenericProductDto getProductById(String idStr) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findById(ProductUtility.createUuidFromString(idStr));
        if (productOptional.isEmpty()) {
            throw new NotFoundException("No product for given uuid: " + idStr);
        }

        return ProductMapper.convertProductEntityToGenericProduct(productOptional.get());
    }

    @Override
    public String createProduct(GenericProductDto productDto) {
        Optional<Category> categoryOptional = categoryRepository.findByName(productDto.getCategory());

        Product product = ProductMapper.convertProductDtoToProductEntity(productDto, categoryOptional);
        productRepository.save(product);
        return "Success";
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product>  productList = productRepository.findAll();
        List<GenericProductDto> productDtos = productList.stream().map(product ->
                        ProductMapper.convertProductEntityToGenericProduct(product))
                        .collect(Collectors.toList());

        return productDtos;
    }

    @Override
    public String deleteProduct(String idStr) throws NotFoundException {
        UUID uuid = ProductUtility.createUuidFromString(idStr);
        Optional<Product> productOptional = productRepository.findById(uuid);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("No product for given uuid: " + idStr);
        }
        productRepository.deleteById(uuid);
        return "product deleted";
    }
}
