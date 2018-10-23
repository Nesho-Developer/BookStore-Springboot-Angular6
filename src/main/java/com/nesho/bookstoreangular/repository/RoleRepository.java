package com.nesho.bookstoreangular.repository;

import com.nesho.bookstoreangular.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
