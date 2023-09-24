package dev.naman.productservice.services;

import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.PriceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import dev.naman.productservice.repositories.SevenApiRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("sevenApiSeviceImpl")
public class SevenApiSeviceImpl implements SevenApiService{
    private SevenApiRepository sevenApiRepository;
    private final CategoryRepository categoryRepository;

    private final PriceRepository priceRepository;

    public SevenApiSeviceImpl(SevenApiRepository sevenApiRepository, CategoryRepository categoryRepository, PriceRepository priceRepository) {
        this.sevenApiRepository = sevenApiRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return sevenApiRepository.findAll();
    }

    @Override
    public Product findProductbyId(String uuid) {
        Optional<Product> productOptional = sevenApiRepository.findById(UUID.fromString(uuid));
        return productOptional.orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String uuid) {
        return sevenApiRepository.findByCategory(UUID.fromString(uuid));
    }

    @Override
    public Product createProduct(Product product) {
        return sevenApiRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, String uuid) {
        Optional<Product> productOptional = sevenApiRepository.findById(UUID.fromString(uuid));
        if(productOptional.isEmpty()){
            return null;
        }
        Product product1 = productOptional.get();
        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setCategory(product.getCategory());
        return sevenApiRepository.save(product1);
    }

    @Override
    public void deleteProduct(String uuid) {
        Optional<Product> productOptional = sevenApiRepository.findById(UUID.fromString(uuid));
        if(productOptional.isEmpty()){
            return;
        }
        sevenApiRepository.delete(productOptional.get());
    }
}
