package com.example.productmanager.exception;

import com.example.productmanager.dto.response.ApiResponse;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandle {
    private static final String MIN_ATTRIBUTE = "min";
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRunTimeException(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse);
    }
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception){
        ErrorCode errorCode = ErrorCode.UNAUTHOZIED;
        return ResponseEntity.status(errorCode.getStatusCode()).body(
                ApiResponse.builder()
                        .code((errorCode.getCode()))
                        .message(errorCode.getMessage())
                        .build()
        );
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> hadlingMethodArgument(MethodArgumentNotValidException exception){

        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        Map<String, Object> attributes = null;
        try{
            errorCode= ErrorCode.valueOf(enumKey);
            var constraintViolation = exception.getBindingResult().getAllErrors().get(0).unwrap(ConstraintViolation.class);
            attributes=constraintViolation.getConstraintDescriptor().getAttributes();
            log.info(attributes.toString());

        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        ApiResponse apiResponse = new ApiResponse();
        System.out.println(errorCode);
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(Objects.nonNull(attributes)?
        mapAttribute(errorCode.getMessage(),attributes):
                errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
    private String mapAttribute(String message, Map<String,Object> attributes){
        String minValue = attributes.get(MIN_ATTRIBUTE).toString();
        return message.replace("{"+MIN_ATTRIBUTE+"}", minValue);
    }

}
