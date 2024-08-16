package com.example.productmanager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    UNCATEGORIZED(9999,"uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_YEAR(1001,"invalid year", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1002,"invalid message key",HttpStatus.BAD_REQUEST),
    USER_EXISTED(1003,"User existed",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1004,"User not existed",HttpStatus.NOT_FOUND),
    USER_NOT_FOUND(1005,"User not found",HttpStatus.BAD_REQUEST),
    USERNAME_VALID(1006,"Username must be at least 4 character",HttpStatus.BAD_REQUEST),
    PASSWORD_VALID(1007,"Password must be at least 4 character",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1008,"unauthenticated",HttpStatus.UNAUTHORIZED),
    UNAUTHOZIED(1009,"you don't have permission",HttpStatus.FORBIDDEN),
    PRODUCT_EXISTED(1010,"Product already existed",HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXISTED(1011,"Product not existed",HttpStatus.NOT_FOUND),
    INVALID_DOB(1012,"Year age atleast {min}",HttpStatus.BAD_REQUEST),
    USERNAME_OR_PASSWORD_WRONG(1013,"Username or password is wrong",HttpStatus.BAD_REQUEST),
    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;
    ErrorCode(int code, String message,HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode =statusCode;

    }

}
