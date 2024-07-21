package com.example.productmanager.service;


import com.example.productmanager.dto.request.UserCreationRequest;
import com.example.productmanager.dto.request.UserUpdateRequest;
import com.example.productmanager.dto.response.UserResponse;
import com.example.productmanager.entity.Role;
import com.example.productmanager.entity.User;
import com.example.productmanager.exception.AppException;
import com.example.productmanager.exception.ErrorCode;
import com.example.productmanager.mapper.UserMapper;
import com.example.productmanager.repository.RoleRepository;
import com.example.productmanager.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class UserService {

}
