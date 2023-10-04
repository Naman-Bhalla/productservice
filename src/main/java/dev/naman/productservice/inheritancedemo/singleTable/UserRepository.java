package com.js.productservice.inheritanceDemo.singleTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("st_ur")
public interface UserRepository
        extends JpaRepository<User, Long> {

    @Override
    <S extends User> S save(S entity);
}