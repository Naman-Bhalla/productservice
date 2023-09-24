package dev.naman.productservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.ProductRepository;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private CategoryServiceImpl categoryService;

    private GenericProductDto convertProductIntoGenericProduct(Product product) {

        GenericProductDto genericproduct = new GenericProductDto();
        genericproduct.setId(product.getId());
        genericproduct.setImage(product.getImage());
        genericproduct.setDescription(product.getDescription());
        genericproduct.setTitle(product.getTitle());
        genericproduct.setPrice(product.getPrice().getPrice());
        genericproduct.setCategory(product.getCategory().getName());

        return genericproduct;
    }

    private Product convertGenericProductToProduct(GenericProductDto inputProduct) {
        Product product = new Product();
        product.setTitle(inputProduct.getTitle());
        product.setDescription(inputProduct.getDescription());
        product.setImage(inputProduct.getImage());
        product.setPrice(new Price("USD", inputProduct.getPrice()));
        Optional<Category> category = categoryRepository.findByName(inputProduct.getCategory());
        Category categoryInfo = new Category();
        if (category.isEmpty()) {
            categoryInfo = categoryRepository.save(categoryService.createCategory(inputProduct.getCategory()));
        }
        product.setCategory(categoryInfo);
        return product;
    }

    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
            CategoryServiceImpl categoryService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Override
    public GenericProductDto getProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return null;
        }
        return convertProductIntoGenericProduct(product.get());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto inputProduct) {
        Optional<Category> category = categoryRepository.findByName(inputProduct.getCategory());
        if (category.isEmpty()) {

        }
        Product product = productRepository.save(convertGenericProductToProduct(inputProduct));
        return convertProductIntoGenericProduct(product);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GenericProductDto> product = new ArrayList<>();
        products.forEach(prod -> {
            GenericProductDto genericproduct = convertProductIntoGenericProduct(prod);
            product.add(genericproduct);
        });
        return product;
    }

    @Override
    public GenericProductDto deleteProduct(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return null;
        }
        Product productInfo = product.get();
        productRepository.delete(productInfo);
        return convertProductIntoGenericProduct(productInfo);
    }

    @Override
    public List<GenericProductDto> getProductsInCategory(List<String> categoryUUID) {
        List<UUID> uuids = new ArrayList();
        categoryUUID.forEach((id) -> {
            uuids.add(UUID.fromString(id));
        });

        List<Category> categories = categoryRepository.findAllById(uuids);
        List<Product> product = productRepository.findAllByCategoryIn(categories);
        List<GenericProductDto> prod = new ArrayList<>();
        product.forEach((item) -> {
            prod.add(convertProductIntoGenericProduct(item));
        });
        return prod;
    }

    @Override
    public GenericProductDto updateProductById(String id, GenericProductDto product) {

        Optional<Product> productInfo = productRepository.findById(UUID.fromString(id));

        if (productInfo.isEmpty()) {
            return null;
        }
        /*
         * if (!product.getId().equals(id)) {
         * return null;
         * }
         */
        Product newProduct = new Product();
        newProduct.setTitle(product.getTitle());
        newProduct.setDescription(product.getDescription());
        newProduct.setImage(product.getImage());
        newProduct.setCategory(categoryService.createCategory(product.getCategory()));
        Price price = new Price("USD", product.getPrice());
        newProduct.setPrice(price);
        productRepository.save(newProduct);
        return convertProductIntoGenericProduct(newProduct);
    }
}
