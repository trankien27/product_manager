package com.example.productmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
     String userID;
    @Column(unique = true)
     String username;
     String password;
     String firstname;
     String lastname;
     String email;
 @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
     LocalDate dob;
     @ManyToMany
     Set<Role> roles ;

    public void setPassword(String password) {
        this.password = password;
    }
}
