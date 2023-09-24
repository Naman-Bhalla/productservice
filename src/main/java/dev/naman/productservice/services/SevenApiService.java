package dev.naman.productservice.services;

import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;

import java.util.List;

public interface SevenApiService {
    List<Product> getAllProducts();
    Product findProductbyId(String uuid);
    List<Category> getAllCategories();
    List<Product> getProductsByCategory(String uuid);
    Product createProduct(Product product);
    Product updateProduct(Product product, String uuid);
    void deleteProduct(String uuid);
}
