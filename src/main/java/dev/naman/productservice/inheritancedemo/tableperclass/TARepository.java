package dev.daliya.productService.inheritancedemo.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tpc_ta")
public interface TARepository extends JpaRepository<TA, Long> {
    @Override
    <S extends TA> S save(S entity);
}
