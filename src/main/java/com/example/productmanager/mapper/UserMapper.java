package com.example.productmanager.mapper;


import com.example.productmanager.dto.request.UserCreationRequest;
import com.example.productmanager.dto.request.UserUpdateRequest;
import com.example.productmanager.dto.response.UserResponse;
import com.example.productmanager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
        @Mapping(target = "roles", ignore = true)
        User toUser(UserCreationRequest request);
        //    User toUser(UserUpdateRequest request);\
        @Mapping(target = "roles", ignore = true)
        void updateUser(@MappingTarget User user, UserUpdateRequest request);
        UserResponse toUserResponse(User user);
}
