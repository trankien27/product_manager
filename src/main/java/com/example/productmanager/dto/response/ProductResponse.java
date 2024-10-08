package com.example.productmanager.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
public class ProductResponse {
    String productId;
    String productName;
    String productDescription;
    String productImage;
    int productQuantity;
    long productPrice;
}
