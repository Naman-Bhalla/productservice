package dev.naman.productservice;

import dev.naman.productservice.inheritancedemo.joinedtable.Mentor;
import dev.naman.productservice.inheritancedemo.joinedtable.MentorRepository;
import dev.naman.productservice.inheritancedemo.joinedtable.User;
import dev.naman.productservice.inheritancedemo.joinedtable.UserRepository;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.PriceRepository;
import dev.naman.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication {// implements CommandLineRunner {

//    private MentorRepository mentorRepository;
//
//
//    private UserRepository userRepository;
//    private final ProductRepository productRepository;
//    private final CategoryRepository categoryRepository;
//    private final PriceRepository priceRepository;
//
//    public ProductserviceApplication(@Qualifier("jt_mr") MentorRepository mentorRepository,
//                                     @Qualifier("jt_ur") UserRepository userRepository,
//                                     ProductRepository productRepository,
//                                     CategoryRepository categoryRepository,
//                                     PriceRepository priceRepository) {
//        this.mentorRepository = mentorRepository;
//        this.userRepository = userRepository;
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//        this.priceRepository = priceRepository;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

//    @Transactional()
//    @Override
//    public void run(String... args) throws Exception {
////        Mentor mentor = new Mentor();
////        mentor.setName("Naman");
////        mentor.setEmail("Naman@scaler.com");
////        mentor.setAverageRating(4.65);
////        mentorRepository.save(mentor);
////
////        User user = new User();
////        user.setName("Sarath");
////        user.setEmail("sarathcool@yopmail.com");
////        userRepository.save(user);
////
////        List<User> users = userRepository.findAll();
////        for (User user1: users) {
////            System.out.println(user1);
////        }
//
//        Category category = new Category();
//        category.setName("Apple Devices");
////        Category savedCategory = categoryRepository.save(category);
//
//        Price price = new Price("Rupee", 10);
////        Price savedPrice = priceRepository.save(price);
//
//        Product product = new Product();
//        product.setTitle("iPhone 15 Pro");
//        product.setDescription("The best iPhone Ever");
//        product.setCategory(category);
//        product.setPrice(price);
//
//        productRepository.save(product);
//
//        productRepository.deleteById(UUID.fromString("95672ed6-3127-4248-ae33-97a261c0a6f4"));
//
//        System.out.println(productRepository.countAllByPrice_Currency("Rupee"));
//        List<Product> products = productRepository.findAllByPrice_Currency("Rupee");
////        Category category1 = categoryRepository.findById(UUID.fromString("5e6f679e-f326-44ae-b220-544b3822ab00")).get();
////        System.out.println("Category name is: " + category1.getName());
////        System.out.println("Printing all products in the category");
//////        Thread.sleep(1000);
////
////        System.out.println(category1.getProducts().size());
////        category1.getProducts().forEach(
////                product1 -> System.out.println(product1.getTitle())
////        );
////
////        for (Product product1: category1.getProducts()) {
////            try {
////                System.out.println(product1.getTitle());
////            } catch (Exception e) {
////                System.out.println(e.getMessage());
////            }
////        }
//
//        List<Product> products1 = productRepository.findAllByTitle("iPhone 15 Pro");
//
//        System.out.println("Fetching category b8f1f459-f9e9-4d3d-b115-f1f5267bd54f");
//        Thread.sleep(1000);
//        Category category1 = categoryRepository.findById(UUID.fromString("b8f1f459-f9e9-4d3d-b115-f1f5267bd54f")).get();
////        Category category1 = category1Optional.get();
//
//        System.out.println("Fetching products for category");
//        Thread.sleep(1000);
//        List<Product> products11 = category1.getProducts();
//    }

}
