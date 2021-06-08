package com.algos.ecommerce.user.repository;

import com.algos.ecommerce.user.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository <UserDetails, Long> {
}