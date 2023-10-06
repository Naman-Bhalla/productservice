package dev.naman.productservice.services;

import dev.pranay.productservice.dtos.GenericProductDtoDB;
import dev.pranay.productservice.exception.NotFoundException;
import dev.pranay.productservice.models.Category;
import dev.pranay.productservice.models.Price;
import dev.pranay.productservice.models.Product;
import dev.pranay.productservice.repositories.CategoryRepository;
import dev.pranay.productservice.repositories.PriceRepository;
import dev.pranay.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductServiceDB{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }
    private GenericProductDtoDB convertProductToGenericProduct(Product product) {

        GenericProductDtoDB genericProductDtoDB = new GenericProductDtoDB();
        genericProductDtoDB.setId(product.getUuid());
        genericProductDtoDB.setImage(product.getImage());
        genericProductDtoDB.setDescription(product.getDescription());
        genericProductDtoDB.setTitle(product.getTitle());
        genericProductDtoDB.setPrice(product.getPrice().getPrice());
        genericProductDtoDB.setCategory(product.getCategory().getName());
        genericProductDtoDB.setCurrency(product.getPrice().getCurrency());

        return genericProductDtoDB;
    }


    @Override
    public GenericProductDtoDB createProduct(GenericProductDtoDB genericProductDtoDB) {
        Category category = categoryRepository.findByName(genericProductDtoDB.getCategory());
        if(category == null){
            category = new Category();
            category.setName(genericProductDtoDB.getCategory());
            categoryRepository.save(category);
        }
        Product product = new Product();
        String currency = (genericProductDtoDB.getCurrency() != null) ? genericProductDtoDB.getCurrency() : "USD";
        Price price = new Price(currency, genericProductDtoDB.getPrice());

        product.setImage(genericProductDtoDB.getImage());
        product.setDescription(genericProductDtoDB.getDescription());
        product.setCategory(category);
        product.setPrice(price);
        product.setImage(genericProductDtoDB.getImage());
        product.setCategory(category);
        product.setTitle(genericProductDtoDB.getTitle());

        priceRepository.save(price);
        Product savedProduct = productRepository.save(product);
        return convertProductToGenericProduct(savedProduct);
    }

    @Override
    public GenericProductDtoDB getProductById(UUID id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return null;
        }
        return convertProductToGenericProduct(product.get());
    }

    @Override
    public List<GenericProductDtoDB> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GenericProductDtoDB> product = new ArrayList<>();
        products.forEach(prod -> {
            GenericProductDtoDB genericproduct = convertProductToGenericProduct(prod);
            product.add(genericproduct);
        });
        return product;
    }

    @Override
    public GenericProductDtoDB deleteProductById(UUID id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new NotFoundException("Product with ID " + id + " not found");
        }
        Product productInfo = product.get();
        productRepository.delete(productInfo);
        return convertProductToGenericProduct(productInfo);
    }

    @Override
    public GenericProductDtoDB updateProductById(GenericProductDtoDB genericProductDto, UUID id) {

        //Product product = ProductConvertor.getProductDto(genericProductDto);
        Product product = productRepository.getById(id);

        product.setTitle(genericProductDto.getTitle());
        product.setDescription(genericProductDto.getDescription());
        Category category = new Category();
        category.setName(genericProductDto.getCategory());
        product.setCategory(category);
        Price price = new Price();
        price.setPrice(genericProductDto.getPrice());
        product.setPrice(price);
        product.setImage(genericProductDto.getImage());

        productRepository.save(product);
        return convertProductToGenericProduct(product);
    }
}
