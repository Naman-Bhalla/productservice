package dev.naman.productservice.inheritancedemo.tablePerClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tpc_mr")
public interface MentorRepository
        extends JpaRepository<Mentor, Long> {
    @Override
    <S extends Mentor> S save(S entity);
}