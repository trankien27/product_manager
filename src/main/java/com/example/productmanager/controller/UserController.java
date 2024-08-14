package com.example.productmanager.controller;

import com.example.productmanager.entity.User;
import com.example.productmanager.exception.AppException;
import com.example.productmanager.repository.UserRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin("http://localhost:3000")
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {

        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();

    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllByPage(page).stream().toList())
                .build();
    }


@GetMapping("/{username}")
ApiResponse<Boolean> checkExistUser(@PathVariable String username) {
        log.info("Check exist user: {}", username);
        return ApiResponse.<Boolean>builder()
                .result(userService.checkExistedUser(username))
                .build();
    }

    @GetMapping("/getmyinfo")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo()).build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
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

