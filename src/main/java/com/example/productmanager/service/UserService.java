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
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById("USER").ifPresent(roles::add);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }
    public UserResponse getMyInfo(){
            var context = SecurityContextHolder.getContext();
            String name = context.getAuthentication().getName();
            log.info(name);
            return userMapper.toUserResponse(userRepository.findByUsername(name).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND)));
    }


    public UserResponse updateUser(String userId,UserUpdateRequest request) {

        User user = userRepository.findById(userId).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userMapper.updateUser(user,request);

       return userMapper.toUserResponse(userRepository.save(user));

    }
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(String userId) {
        if(!userRepository.existsById(userId))
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        userRepository.deleteById(userId);
        return "user was been deleted";
    }
}
