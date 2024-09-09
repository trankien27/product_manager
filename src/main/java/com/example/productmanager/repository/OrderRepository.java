package com.example.productmanager.repository;

import com.example.productmanager.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,String> {
    @Query("SELECT o FROM Orders o WHERE o.username = ?1")
    public List<Orders> findOrdersByUsername(String username);

}
