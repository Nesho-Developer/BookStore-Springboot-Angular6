package com.nesho.bookstoreangular.repository;

import com.nesho.bookstoreangular.domain.ShippingAddress;
import org.springframework.data.repository.CrudRepository;

public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long> {
}
