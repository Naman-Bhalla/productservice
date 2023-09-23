package dev.daliya.productService;

import dev.daliya.productService.models.Category;
import dev.daliya.productService.models.Price;
import dev.daliya.productService.models.Product;
import dev.daliya.productService.repositories.CategoryRepository;
import dev.daliya.productService.repositories.PriceRepository;
import dev.daliya.productService.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    private PriceRepository priceRepository;

//    private MentorRepository mentorRepository;
//    private UserRepository userRepository;
//
//    private TARepository taRepository;
//
//    public ProductServiceApplication(@Qualifier("tpc_mr") MentorRepository mentorRepository,
//                                     @Qualifier("tpc_ur") UserRepository userRepository,
//                                     @Qualifier("tpc_ta") TARepository taRepository) {
//        this.mentorRepository = mentorRepository;
//        this.userRepository = userRepository;
//        this.taRepository = taRepository;
    // }

    public ProductServiceApplication(CategoryRepository categoryRepository,
                                     ProductRepository productRepository, PriceRepository priceRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
//        TA mentor = new TA();
//        mentor.setName("Daliya");
//        mentor.setEmail("daliyajohnson33@gmail.com");
//        mentor.setAverageRating(90);
//        System.out.println(mentor);
//        taRepository.save(mentor);

//		User user = new User();
//		user.setName("Dalia");
//		user.setEmail("daliyajohnson33@gmail.com");
//		userRepository.save(user);

        Price price = new Price();
        price.setPrice(Double.valueOf("1000"));
        price.setCurrency("USD");

        Category category = new Category();
        category.setName("Electronics");
        category.setDescription("Electronics");

        Product product = new Product();
        product.setTitle("Laptop");
        product.setDescription("Laptop");
        product.setPrice(price);
        product.setCategory(category);
        productRepository.save(product);

        Price price1 = new Price();
        price1.setPrice(Double.valueOf("2000"));
        price1.setCurrency("USD");

        Category category1 = new Category();
        category1.setName("Skin Care");
        category1.setDescription("Skin Care");

        Product product1 = new Product();
        product1.setTitle("Lotion");
        product1.setDescription("Lotion");
        product1.setPrice(price1);
        product1.setCategory(category1);
        productRepository.save(product1);

    }
}
