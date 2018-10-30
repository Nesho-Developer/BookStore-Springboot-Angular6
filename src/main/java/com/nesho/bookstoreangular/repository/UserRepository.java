package com.nesho.bookstoreangular.repository;

import com.nesho.bookstoreangular.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    Iterable<User> findAll();

    Optional<User> findById(Long id);

}
