package com.nesho.bookstoreangular.repository;

import com.nesho.bookstoreangular.domain.UserBilling;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBillingRepository extends CrudRepository<UserBilling, Long> {
}
