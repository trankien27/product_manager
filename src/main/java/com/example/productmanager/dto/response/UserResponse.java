package com.example.productmanager.dto.response;

import com.example.productmanager.entity.Role;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
     String userID;
     String username;
     String firstname;
     String lastname;
     String email;
     LocalDate dob;
     @ManyToMany
     Set<Role> roles ;

}
