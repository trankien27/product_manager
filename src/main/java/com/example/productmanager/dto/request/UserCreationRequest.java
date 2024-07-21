package com.example.productmanager.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
//    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDate dob;

}
