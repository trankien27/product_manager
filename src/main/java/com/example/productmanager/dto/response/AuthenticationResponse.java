package com.example.productmanager.dto.response;

import com.example.productmanager.entity.Role;
import com.example.productmanager.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    String token;
    boolean authenticated;

String username;
Set<Role> roles;
}
