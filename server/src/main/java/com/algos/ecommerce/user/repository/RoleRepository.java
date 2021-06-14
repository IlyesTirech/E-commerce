package com.algos.ecommerce.user.repository;


import com.algos.ecommerce.user.model.ERole;
import com.algos.ecommerce.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}