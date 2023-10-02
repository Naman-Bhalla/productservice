package dev.biswajit.ecomm.productservice.repository;

import dev.biswajit.ecomm.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where lower(c.title) like lower(concat('%', :title, '%') ) ")
    Optional<Category> findByTitle(@Param("title") String title);

}
