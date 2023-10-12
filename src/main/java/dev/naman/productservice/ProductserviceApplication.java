package dev.naman.productservice;

//import dev.naman.productservice.inheritancedemo.joinedtable.Mentor;
//import dev.naman.productservice.inheritancedemo.joinedtable.MentorRepository;
//import dev.naman.productservice.inheritancedemo.joinedtable.User;
//import dev.naman.productservice.inheritancedemo.joinedtable.UserRepository;
//import dev.naman.productservice.models.Category;
//import dev.naman.productservice.models.Price;
//import dev.naman.productservice.models.Product;
//import dev.naman.productservice.repositories.CategoryRepository;
//import dev.naman.productservice.repositories.PriceRepository;
//import dev.naman.productservice.repositories.ProductRepository;
//import jakarta.transaction.Transactional;
//import org.hibernate.Hibernate;
//import org.springframework.beans.factory.annotation.Qualifier;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

//    @Autowired
//    private static Environment environment;
//    public ProductserviceApplication(Environment environment) {
//        this.environment = environment;
//    }

//    private static String serverPort = environment.getProperty("server.port");

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
        // Retrieve server.port property
//        System.out.println("SERVER RUNNING ON PORT : "+ serverPort);
        System.out.println("******************************");
        System.out.println("SERVER RUNNING ON PORT: "+ 4444);
        System.out.println("******************************");
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
//        System.out.println("From command line runner");
    }
}
