package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.mapper.ProductMapper;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


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
    public String createProduct(GenericProductDto productDto) {
        Optional<Category> categoryOptional = categoryRepository.findByName(productDto.getCategory());

        Product product = ProductMapper.convertProductDtoToProductEntity(productDto, categoryOptional);
        productRepository.save(product);
        return "Success";
    }

    @Override
    public GenericProductDto getProductById(String idStr) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findById(UUID.fromString(idStr));
        if (productOptional.isEmpty()) {
            throw new NotFoundException("No product for given uuid: " + idStr);
        }

        return ProductMapper.convertProductEntityToGenericProduct(productOptional.get());
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product>  productList = productRepository.findAll();
        return productList.stream().map(
                        ProductMapper::convertProductEntityToGenericProduct).toList();

    }

    @Override
    public String deleteProduct(String idStr) throws NotFoundException {

        Optional<Product> productOptional = productRepository.findById(UUID.fromString(idStr));
        if (productOptional.isEmpty()) {
            throw new NotFoundException("No product for given uuid: " + idStr);
        }
        productRepository.deleteById(UUID.fromString(idStr));
        return "product deleted";
    }

    @Override
    public GenericProductDto updateProduct(GenericProductDto productDto) throws NotFoundException {
        UUID uuid=UUID.fromString(productDto.getId());
        Optional<Product> productOptional = productRepository.findById(uuid);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("No product for given uuid: " + productDto.getId());
        }
        Product productEntity = updateProductDetails(productDto, productOptional.get());
        productRepository.save(productEntity);

        return getProductById(productDto.getId());
    }

    private Product updateProductDetails(GenericProductDto productDto, Product product) {
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.getPrice().setPrice(productDto.getPrice());

        if (!productDto.getCategory().equalsIgnoreCase(product.getCategory().getName())) {
            Optional<Category> categoryOptional = categoryRepository.findByName(productDto.getCategory());
            product.setCategory(ProductMapper.getProductCategory(productDto.getCategory(), categoryOptional, product));
        }

        return product;
    }
}
