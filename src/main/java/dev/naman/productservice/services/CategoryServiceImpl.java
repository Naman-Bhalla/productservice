package dev.naman.productservice.services;

import dev.naman.productservice.dtos.CategoryDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Category getCategory(String uuid) {
       // Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));

      //  if (categoryOptional.isEmpty()) {
      //      return null;
      //  }

       // Category category = categoryOptional.get();

      //  List<Product> products = category.getProducts();


       // return category;
        return null;
    }

    public List<String> getProductTitles(List<String> categoryUUIDs) {
        List<UUID> uuids = new ArrayList<>();

        for (String uuid: categoryUUIDs) {
            uuids.add(UUID.fromString(uuid));
        }
//
//        List<Category> categories = categoryRepository.findAllById(uuids);
//
//
//        List<String> titles = new ArrayList<>();
//
//        categories.forEach(
//                category -> {
//                    category.getProducts().forEach(
//                            product -> {
//                                titles.add(product.getTitle());
//                            }
//                    );
//                }
//        );
//
//
//        return titles;

     //   List<Category> categories = categoryRepository.findAllById(UUID.fromString(" "));

    //    List<Product> products = productRepository.findAllByCategoryIn(categories);

        List<String> titles = new ArrayList<>();

       // for (Product p: products) {
      //      titles.add(p.getTitle());
      //  }

        return titles;
    }

    @Override
    public List<String> getAllCategory() {

        List<Category> categoty = categoryRepository.findAll();

        List<String> result = categoty.stream().map(category -> category.getName()).collect(Collectors.toList());
        return result;
    }

    @Override
    public CategoryDto findCategoryById(Long id) {
        Category category = null;
        CategoryDto categoryDto = new CategoryDto();
        try {
            category= categoryRepository.findById(id).get();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
       return convert(category);
    }

    private CategoryDto convert(Category category){
        CategoryDto categoryDto= new CategoryDto();
        ProductDto productDto= new ProductDto();
        List<ProductDto> products= new ArrayList<>();
        categoryDto.setName(category.getName());
        for (Product  product: category.getProducts()){
            productDto.setDescription(product.getDescription());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice());
            productDto.setTitle(product.getTitle());
            products.add(productDto);
        }

        categoryDto.setProducts(products);
        return categoryDto;
    }
}
