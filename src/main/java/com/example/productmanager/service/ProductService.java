package com.example.productmanager.service;

import com.example.productmanager.dto.request.ProductCreationRequest;
import com.example.productmanager.dto.request.ProductUpdateRequest;
import com.example.productmanager.dto.response.ProductResponse;
import com.example.productmanager.entity.Product;
import com.example.productmanager.exception.AppException;
import com.example.productmanager.exception.ErrorCode;
import com.example.productmanager.mapper.ProductMapper;

import com.example.productmanager.repository.ProductRepository;
import com.example.productmanager.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse createProduct(ProductCreationRequest request) {
        if (productRepository.existsProductByProductName(request.getProductName()))
            throw new AppException(ErrorCode.PRODUCT_EXISTED);


        Product product = productMapper.toProduct(request);
        log.info(product.toString());
        return productMapper.toProductResponse(productRepository.save(product));

    }
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updateProduct(ProductUpdateRequest request,String productId) {
        Product product = productRepository.findByProductId(productId).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        productMapper.updateProduct(product,request);
        return productMapper.toProductResponse(productRepository.save(product));
    }

//    public List<ProductResponse> getAllProducts() {
//        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
//    }

    public Page<ProductResponse> getAllByPage(final Pageable pageable) {
        final Page<Product> page = productRepository.findAll(pageable);

      return page.map(productMapper::toProductResponse);
    }


    public ProductResponse getProduct(String productId) {
        return productMapper.toProductResponse(productRepository.findByProductId(productId).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_EXISTED)));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteProduct(String productId) {
     Product product=   productRepository.findByProductId(productId).orElseThrow(()->new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        productRepository.deleteById(productId);

        return "Product with name "+product.getProductName()+" was deleted";
    }

}
