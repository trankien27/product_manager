package com.example.productmanager.controller;

import com.example.productmanager.dto.request.OrderRequest;
import com.example.productmanager.dto.response.ApiResponse;
import com.example.productmanager.entity.Orders;
import com.example.productmanager.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin("http://localhost:3000")
public class OrderController {
    OrderService orderService;
    @PostMapping
    ApiResponse<Orders> createOrder(@RequestBody OrderRequest orderRequest){
        return ApiResponse.<Orders>builder()
                .result(orderService.createOrder(orderRequest))
                .build();
    }
    @GetMapping
ApiResponse<List<Orders>> getAllOrders(){
        return ApiResponse.<List<Orders>>builder()
                .result(orderService.getAllOrders())
                .build();
    }
    @GetMapping("/by-user")
    ApiResponse<List<Orders>> getAllOrdersOfUsername(){
        return ApiResponse.<List<Orders>>builder()
                .result(orderService.getOrdersOfUser())
                .build();
    }
    @DeleteMapping("/{orderId}")
    ApiResponse<String> deleteOrder(@PathVariable String orderId){
        return ApiResponse.<String>builder()
                .result(orderService.deleteOrder(orderId))
                .build();
    }
 }
