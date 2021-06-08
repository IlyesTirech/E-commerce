package com.algos.ecommerce.user.repository;

import com.algos.ecommerce.user.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address, Long> {
}