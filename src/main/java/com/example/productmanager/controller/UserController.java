package com.example.productmanager.controller;

import com.example.productmanager.service.UserService;
import com.example.productmanager.dto.response.ApiResponse;
import com.example.productmanager.dto.request.UserCreationRequest;
import com.example.productmanager.dto.request.UserUpdateRequest;
import com.example.productmanager.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
@PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();

}
@GetMapping
ApiResponse<List<UserResponse>> getUsers() {
    return ApiResponse.<List<UserResponse>>builder()
            .result(userService.getAllUsers())
            .build();
}

@PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request) {
    return ApiResponse.<UserResponse>builder()
            .result(userService.updateUser(userId,request))
            .build();
}
@DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId) {
    return ApiResponse.<String>builder()
            .result(userService.deleteUser(userId))
            .build()
            ;
}
}

