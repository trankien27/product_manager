package com.example.productmanager.controller;

import com.example.productmanager.dto.request.ProductCreationRequest;
import com.example.productmanager.dto.request.ProductUpdateRequest;
import com.example.productmanager.dto.response.ApiResponse;
import com.example.productmanager.dto.response.ProductResponse;
import com.example.productmanager.repository.ProductRepository;
import com.example.productmanager.service.ProductService;

import com.example.productmanager.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin("http://localhost:3000")
public class ProductController {
    ProductService productService;
    ProductRepository productRepository;
    private final UserService userService;


    @PostMapping
    ApiResponse<ProductResponse> createProduct(@RequestBody ProductCreationRequest request) {
        log.info(request.toString());
        return ApiResponse.<ProductResponse>builder()

                .result(productService.createProduct(request))
                .build();
    }
    @GetMapping
    ApiResponse<List<ProductResponse>> getAllProducts(
//            @PathVariable int pageNo ,
//            @PathVariable int pageSize
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        Pageable pageable =PageRequest.of(pageNo, pageSize);
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllByPage(pageable).stream().toList())
//                .result(productService.getAllProducts())
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
