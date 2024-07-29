package com.example.productmanager.entity;

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
     String username;
     String password;
     String firstname;
     String lastname;
     String email;
     LocalDate dob;
     @ManyToMany
     Set<Role> roles ;


}
