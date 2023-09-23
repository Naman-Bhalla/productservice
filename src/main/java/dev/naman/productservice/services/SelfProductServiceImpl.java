package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.PriceRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    private PriceRepository priceRepository;

    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }
    private GenericProductDto convertProductToGenericProduct(Product product) {

        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(product.getUuid().toString());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setPrice(product.getPrice().getPrice());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setCurrency(product.getPrice().getCurrency());

        return genericProductDto;
    }


    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {
        Optional<Product> productObj = productRepository.findById(id);
        if(productObj.isEmpty()){
            throw new NotFoundException("Product with ID " + id + " not found");
        }
        Product product = productObj.get();
        return convertProductToGenericProduct(product);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {

        Category category = categoryRepository.findByName(genericProductDto.getCategory());
        if(category == null){
            category = new Category();
            category.setName(genericProductDto.getCategory());
            categoryRepository.save(category);
        }
        Product product = new Product();
        String currency = (genericProductDto.getCurrency() != null) ? genericProductDto.getCurrency() : "USD";
        Price price = new Price(currency, genericProductDto.getPrice());

        product.setImage(genericProductDto.getImage());
        product.setDescription(genericProductDto.getDescription());
        product.setCategory(category);
        product.setPrice(price);
        product.setImage(genericProductDto.getImage());
        product.setCategory(category);
        product.setTitle(genericProductDto.getTitle());

        priceRepository.save(price);
        Product savedProduct = productRepository.save(product);
        return convertProductToGenericProduct(savedProduct);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

      List<Product> productList = productRepository.findAllProducts();

        return productList.stream()
                .map(this::convertProductToGenericProduct)
                .collect(Collectors.toList());
    }

    @Override
    public GenericProductDto deleteProduct(UUID id) throws NotFoundException{
        Optional<Product> productObj = productRepository.findById(id);
        if(productObj.isEmpty()){
            throw new NotFoundException("Product with ID " + id + " not found");
        }
        Product product = productObj.get();
        GenericProductDto genericProductDto = convertProductToGenericProduct(product);

        productRepository.deleteById(id);
        return genericProductDto;
//


    }

    @Override
    public GenericProductDto updateProduct(UUID id, GenericProductDto genericProductDto) throws NotFoundException {

        Optional<Product> productObj = productRepository.findById(id);

        if(productObj.isEmpty()){
            return null;
        }
        Product product = productObj.get();
        Price price = product.getPrice();

        if(genericProductDto.getTitle()!=null){
            product.setTitle(genericProductDto.getTitle());
        }
        if(genericProductDto.getDescription() != null){
            product.setDescription(genericProductDto.getDescription());
        }
        if(genericProductDto.getImage() != null){
            product.setImage(genericProductDto.getImage());
        }
        if(genericProductDto.getCategory() != null){
            Category category = categoryRepository.findByName(genericProductDto.getCategory());
            if(category == null){
                category = new Category();
                category.setName(genericProductDto.getCategory());
                categoryRepository.save(category);
            }
            product.setCategory(category);
        }
        if(genericProductDto.getPrice() != null){
            price.setPrice(genericProductDto.getPrice());
        }
        if(genericProductDto.getCurrency() != null){
            price.setCurrency(genericProductDto.getCurrency());
        }
        productRepository.save(product);
        priceRepository.save(price);


        return convertProductToGenericProduct(product);

    }

    @Override
    public List<String> getProductCategories() {
        return productRepository.getAllProductCategory();
    }

    @Override
    public List<GenericProductDto> getProductInCategory(String categoryName) {
        List<Product> productList = productRepository.getAllProductByCategory(categoryName);

        return productList.stream().map(this::convertProductToGenericProduct).collect(Collectors.toList());
    }

}
