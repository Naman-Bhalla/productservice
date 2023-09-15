package dev.naman.productservice.inheritancedemo.singletable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
extends JpaRepository<User, Long> {

    @Override
    <S extends User> S save(S entity);
}
