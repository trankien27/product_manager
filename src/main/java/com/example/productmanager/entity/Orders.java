package com.example.productmanager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String OrderId;
    String username;
    String productName;
    int productQuantity;
    String fullname;
    String email;
    String numberPhone;
    String payment;
    String province;
    String district;
    String note;
}
