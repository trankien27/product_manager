package com.example.productmanager.mapper;

import com.example.productmanager.dto.request.ProductCreationRequest;
import com.example.productmanager.dto.request.ProductUpdateRequest;
import com.example.productmanager.dto.response.ProductResponse;
import com.example.productmanager.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductCreationRequest request);
void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);
ProductResponse toProductResponse(Product product);

}
