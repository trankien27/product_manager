package com.example.productmanager.dto.response;

import com.example.productmanager.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

     @JsonFormat(pattern="dd-MM-yyyy")
     @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
     public LocalDate getDob() {
          return dob;
     }
}
