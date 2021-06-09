package com.algos.ecommerce.product.repository;

import com.algos.ecommerce.product.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<Image, String> {
}