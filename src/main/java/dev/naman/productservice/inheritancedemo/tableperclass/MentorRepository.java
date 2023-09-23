package dev.naman.productservice.inheritancedemo.tableperclass;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tpc_mr")
public interface MentorRepository
extends JpaRepository<Mentor, Long> {
    @Override
    <S extends Mentor> S save(S entity);
}
