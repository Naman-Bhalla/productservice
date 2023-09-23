package dev.daliya.productService.inheritancedemo.mappedsuperclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ms_ta")
public interface TARepository extends JpaRepository<TA, Long> {
    @Override
    <S extends TA> S save(S entity);
}
