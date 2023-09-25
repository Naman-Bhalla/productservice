package dev.naman.productservice.services;

import dev.naman.productservice.convertor.Convertor;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
    private Convertor convertor;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductServiceImpl(RestTemplateBuilder restTemplateBuilder, Convertor convertor,
                                  ProductRepository productRepository,
                                  CategoryRepository categoryRepository){
        this.restTemplateBuilder = restTemplateBuilder;
        this.convertor = convertor;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<GenericProductDto> getAllProducts() throws NotFoundException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            throw new NotFoundException("The products list is empty! Please try later!");
        }
        List<GenericProductDto> genericProducts = new ArrayList<>();
        for(Product product: products){
            genericProducts.add(convertor.convertProductToGenericProductDto(product));
        }
        return genericProducts;
    }
    @Override
    public GenericProductDto getProductById(Long id)throws NotFoundException {
        return null;
    }
    @Override
    public GenericProductDto getProductById(UUID id)throws NotFoundException {
        Optional<Product> fetchedProduct = productRepository.findById(id);
        if(fetchedProduct.isEmpty()){
            throw new NotFoundException("The product with id: "+id.toString()+" does not exist!");
        }
        Product product = fetchedProduct.get();
        GenericProductDto genericProductDto = convertor.convertProductToGenericProductDto(product);
        return genericProductDto;
    }
    @Override
    public GenericProductDto deleteProductById(Long id)throws NotFoundException {
        return null;
    }
    @Override
    public GenericProductDto deleteProductById(UUID id) throws NotFoundException{
        Optional<Product> fetchedProduct = productRepository.findById(id);
        if(fetchedProduct.isEmpty()){
            throw new NotFoundException("The product with id: "+id.toString()+" does not exist!");
        }
        GenericProductDto genericProduct = convertor.convertProductToGenericProductDto(fetchedProduct.get());
        productRepository.deleteById(id);
        return genericProduct;
    };
    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto){
        Product product = convertor.convertGenericProductToProduct(genericProductDto);
        productRepository.save(product);
        genericProductDto.setId(product.getUuid().toString());
        return genericProductDto;
    }
    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto product) throws NotFoundException{
        return null;
    }
    @Override
    public GenericProductDto updateProductById(UUID id, GenericProductDto genericProduct) throws NotFoundException {
        Optional<Product> fetchedProduct = productRepository.findById(id);
        if(fetchedProduct.isEmpty()){
            throw new NotFoundException("The product with id: "+id.toString()+" does not exist!");
        }
        Product product = fetchedProduct.get();
        product.setTitle(genericProduct.getTitle());
        product.setDescription(genericProduct.getDescription());
        product.setImage(genericProduct.getImage());
        product.setPrice(genericProduct.getPrice());
        product.setCurrency(genericProduct.getCurrency());
        if(!product.getCategory().getName().equals(genericProduct.getCategory())){
            Category currentCategory = product.getCategory();
            List<Product> currentProductList = currentCategory.getProducts();
            Iterator<Product> iterator = currentProductList.iterator();

            while (iterator.hasNext()) {
                Product product1 = iterator.next();
                if (product.equals(product1)) {
                    iterator.remove(); // Remove the element using the iterator
                }
            }
            categoryRepository.save(currentCategory);
            Optional<Category> fetchedCategory = categoryRepository.findByName(genericProduct.getCategory());
            Category category = null;
            if(fetchedCategory.isEmpty()){
                category = new Category();
                category.setName(genericProduct.getCategory());
                List<Product> products = new ArrayList<>();
                products.add(product);
                category.setProducts(products);
            }
            else{
                category = fetchedCategory.get();
                List<Product> products = category.getProducts();
                products.add(product);
                category.setProducts(products);
            }
            product.setCategory(category);
        }
        productRepository.save(product);
        return convertor.convertProductToGenericProductDto(product);
    }
}