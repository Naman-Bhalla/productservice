package com.js.productservice.inheritanceDemo.joinedTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jt_mr")
public interface MentorRepository
        extends JpaRepository<Mentor, Long> {
    @Override
    <S extends Mentor> S save(S entity);
}