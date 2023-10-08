package dev.naman.productservice.services;

import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.ProductRepository;
import dev.naman.productservice.repositories.SevenApiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("sevenApiService")
public class SevenApiServiceImpl implements SevenApiService{
   private SevenApiRepository sevenApiRepository;
   private ProductRepository productRepository;
   private final CategoryRepository categoryRepository;

   public SevenApiServiceImpl(SevenApiRepository sevenApiRepository , ProductRepository productRepository,
                              CategoryRepository categoryRepository){
      this.sevenApiRepository = sevenApiRepository;
      this.productRepository = productRepository;
      this.categoryRepository = categoryRepository;
   }
   private ProductDto convertProductToProductDto(Product product) {

      ProductDto productDto = new ProductDto();
      product.setDescription(productDto.getDescription());
      product.setTitle(productDto.getTitle());
      product.setImage(productDto.getImage());
      product.setPrice(productDto.getPrice());

      return productDto;
   }
   @Override
   public List<Product> getAllProducts(){
      List<Product> products = productRepository.findAll();
      if(products.isEmpty()){
         return null;
      }
      return products;
   }
   @Override
   public Product getProductById(String uuid){
      Optional<Product> productOptional = productRepository.findById(UUID.fromString(uuid));
      if(productOptional.isEmpty()){
         return null;
      }
      Product product = productOptional.get();
      return product;
   }
   @Override
   public Category getAllProductsByCategory(String uuid){
      Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));
      if(categoryOptional.isEmpty()){
         return null;
      }
      Category category = categoryOptional.get();
      List<Product> products = category.getProducts();

      return category;

   }
   @Override
   public List<Category> getAllCategories(){
      return categoryRepository.findAll();
   }
   @Override
   public Product addProduct(Product product){
      sevenApiRepository.save(product);
      return product;
   }
   @Override
   public Product updateProduct(Product product , String uuid){
      Optional<Product> productOptional = sevenApiRepository.findById(UUID.fromString(uuid));
      if(productOptional.isEmpty()){
         sevenApiRepository.save(product);
         return product;
      }
      Product prod = productOptional.get();
      prod.setDescription(product.getDescription());
      prod.setTitle(product.getTitle());
      prod.setImage(product.getImage());
      prod.setPrice(product.getPrice());
      prod.setCategory(product.getCategory());
      return sevenApiRepository.save(prod);
   }
   @Override
   public String deleteProduct(String uuid){
      Optional<Product> productOptional = sevenApiRepository.findById(UUID.fromString(uuid));
      if(productOptional.isEmpty()){
         return "Product not found";
      }
      Product product = productOptional.get();
      sevenApiRepository.delete(product);
      return "Product deleted";
   }
}
