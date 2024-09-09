package com.example.productmanager.service;

import com.example.productmanager.dto.request.OrderRequest;
import com.example.productmanager.entity.Orders;
import com.example.productmanager.repository.OrderRepository;
import com.example.productmanager.repository.ProductRepository;
import com.example.productmanager.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class OrderService {
    OrderRepository orderRepository;
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public Orders createOrder(OrderRequest request){
    var context = SecurityContextHolder.getContext();
    String name = context.getAuthentication().getName();
    Orders orders = Orders.builder()
            .productQuantity(request.getQuantityProduct())
            .productName(request.getProductName())
            .username(name)
            .build();
            return orderRepository.save(orders);



}
@PreAuthorize("hasRole('ADMIN')")
public List<Orders> getAllOrders(){
    return orderRepository.findAll();
}
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Orders> getOrdersOfUser(){
    var context = SecurityContextHolder.getContext();
    String username = context.getAuthentication().getName();
        return orderRepository.findOrdersByUsername(username);
    }

@PreAuthorize("hasRole('ADMIN')")
    public String deleteOrder(String orderId){
    orderRepository.deleteById(orderId);
    return "Đã xoá thành công!";
}

}
