package dev.naman.productservice.services;

import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;

import java.util.List;

public interface SevenApiService {
    Product getProductById(String id);
    List<Product> getAllProducts();

    Category getAllProductsByCategory(String uuid);
    List<Category> getAllCategories();
    Product addProduct(Product product);
    Product updateProduct(Product peoduct , String uuid);

    String deleteProduct(String uuid);

}
