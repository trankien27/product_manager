package com.example.productmanager.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    String productName;
    int quantityProduct;
    String fullname;
    String numberPhone;
    String payment;
    String province;
    String district;
    String note;
    String email;
}
