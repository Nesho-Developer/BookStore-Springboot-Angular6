package com.nesho.bookstoreangular.repository;

import com.nesho.bookstoreangular.domain.BillingAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingAddressRepository extends CrudRepository<BillingAddress, Long> {
}
