package com.example.productmanager.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {


    @Builder.Default
    int code = 1000;
    int quantity;
    String message;
    T result;

}
