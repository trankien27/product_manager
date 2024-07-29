package com.example.productmanager.repository;

import com.example.productmanager.entity.Product;
import com.example.productmanager.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Set<Role> findByName(String Name);

}
