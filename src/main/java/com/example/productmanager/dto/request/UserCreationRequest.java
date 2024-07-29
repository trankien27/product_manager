package com.example.productmanager.dto.request;

import com.example.productmanager.entity.Role;
import com.example.productmanager.validator.DobConstraint;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_VALID")
     String username;
    @Size(min = 4, message = "PASSWORD_VALID")
     String password;
     String firstname;
    String lastname;
    String email;
//    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @DobConstraint(min = 17,message = "INVALID_DOB")
    LocalDate dob;

    @ManyToMany
    Set<Role> roles ;
}
