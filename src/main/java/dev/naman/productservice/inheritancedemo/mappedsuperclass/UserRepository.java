package dev.daliya.productService.inheritancedemo.mappedsuperclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ms_ur")
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    <S extends User> S save(S entity);

}
