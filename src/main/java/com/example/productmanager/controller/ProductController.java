package com.example.productmanager.controller;

import com.example.productmanager.dto.request.ProductCreationRequest;
import com.example.productmanager.dto.request.ProductUpdateRequest;
import com.example.productmanager.dto.response.ApiResponse;
import com.example.productmanager.dto.response.ProductResponse;
import com.example.productmanager.service.ProductService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class ProductController {
    ProductService productService;


    @PostMapping
    ApiResponse<ProductResponse> createProduct(@RequestBody ProductCreationRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.createProduct(request))
                .build();
    }
    @GetMapping
    ApiResponse<List<ProductResponse>> getAllProducts() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProducts())
                .build();
    }

    @PutMapping("/{productId}")
 ProductResponse updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable String productId) {
        return productService.updateProduct(request, productId);
    }
    @DeleteMapping("{productId}")
    String deleteProduct(@PathVariable String productId) {
        return productService.deleteProduct(productId);
    }
}
