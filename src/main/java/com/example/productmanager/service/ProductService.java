package com.example.productmanager.service;

import com.example.productmanager.dto.request.ProductCreationRequest;
import com.example.productmanager.dto.request.ProductUpdateRequest;
import com.example.productmanager.dto.response.ProductResponse;
import com.example.productmanager.entity.Product;
import com.example.productmanager.exception.AppException;
import com.example.productmanager.exception.ErrorCode;
import com.example.productmanager.mapper.ProductMapper;
import com.example.productmanager.mapper.ProductMapperImpl;
import com.example.productmanager.repository.ProductRepository;
import com.example.productmanager.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;
    private final ProductMapperImpl productMapperImpl;

    public ProductResponse createProduct(ProductCreationRequest request) {
        if (productRepository.existsProductByProductName(request.getProductName()))
            throw new AppException(ErrorCode.PRODUCT_EXISTED);


        Product product = productMapper.toProduct(request);
        return productMapper.toProductResponse(productRepository.save(product));

    }
    public ProductResponse updateProduct(ProductUpdateRequest request,String productId) {
        Product product = productRepository.findByProductId(productId).orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        productMapper.updateProduct(product,request);
        return productMapper.toProductResponse(productRepository.save(product));
    }
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }
    public String deleteProduct(String productId) {
     Product product=   productRepository.findByProductId(productId).orElseThrow(()->new AppException(ErrorCode.PRODUCT_NOT_EXISTED));
        productRepository.deleteById(productId);

        return "Product with name "+product.getProductName()+" was deleted";
    }

}
