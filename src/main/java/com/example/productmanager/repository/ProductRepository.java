package com.example.productmanager.repository;

import com.example.productmanager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsProductByProductName(String productName);
    Optional<Product> findByProductId(String productId);
    Product findByProductName(String productName);


}
