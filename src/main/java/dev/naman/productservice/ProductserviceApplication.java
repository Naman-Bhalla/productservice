package dev.naman.productservice;

import dev.naman.productservice.inheritancedemo.joinedtable.Mentor;
import dev.naman.productservice.inheritancedemo.joinedtable.MentorRepository;
import dev.naman.productservice.inheritancedemo.joinedtable.User;
import dev.naman.productservice.inheritancedemo.joinedtable.UserRepository;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

    private MentorRepository mentorRepository;


    private UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductserviceApplication(@Qualifier("jt_mr") MentorRepository mentorRepository,
                                     @Qualifier("jt_ur") UserRepository userRepository,
                                     ProductRepository productRepository,
                                     CategoryRepository categoryRepository) {
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Mentor mentor = new Mentor();
//        mentor.setName("Naman");
//        mentor.setEmail("Naman@scaler.com");
//        mentor.setAverageRating(4.65);
//        mentorRepository.save(mentor);
//
//        User user = new User();
//        user.setName("Sarath");
//        user.setEmail("sarathcool@yopmail.com");
//        userRepository.save(user);
//
//        List<User> users = userRepository.findAll();
//        for (User user1: users) {
//            System.out.println(user1);
//        }

        Category category = new Category();
        category.setName("Apple Devices");
        Category savedCategory = categoryRepository.save(category);

        Product product = new Product();
        product.setTitle("iPhone 15 Pro");
        product.setDescription("The best iPhone Ever");
        product.setCategory(savedCategory);

        productRepository.save(product);

        Category category1 = categoryRepository.findById(UUID.fromString("5e6f679e-f326-44ae-b220-544b3822ab00")).get();
        System.out.println("Category name is: " + category1.getName());
        System.out.println("Printing all products in the category");
        Thread.sleep(1000);
//
//        category1.getProducts().forEach(
//                product1 -> System.out.println(product1.getTitle())
//        );

//        for (Product product1: category1.getProducts()) {
//            try {
//                System.out.println(product1.getTitle());
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
    }
}
