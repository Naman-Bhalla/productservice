package dev.naman.productservice.inheritancedemo.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository
extends JpaRepository<Mentor, Long> {
    @Override
    <S extends Mentor> S save(S entity);
}
