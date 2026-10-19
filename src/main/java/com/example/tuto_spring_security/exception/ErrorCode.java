package com.example.tuto_spring_security.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND),
    CHANGE_PASSWORD_MISMATCH("CHANGE_PASSWORD_MISMATCH", "Confirm password and new password are not the same", HttpStatus.BAD_REQUEST),
    INVALID_CURRENT_PASSWORD("INVALID_CURRENT_PASSWORD", "Current password is invalid", HttpStatus.BAD_REQUEST),
    ACCOUNT_ALREADY_DEACTIVATED("ACCOUNT_ALREADY_DEACTIVATED", "Account is already deactivated", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXIST("EMAIL_ALREADY_EXIST","An account already exist with this email", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_ALREADY_EXIST("PHONE_NUMBER_ALREADY_EXIST", "An account already exist with this phone number", HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH("PASSWORD_MISMATCH", "Password and confirm password are not the same", HttpStatus.BAD_REQUEST),
    ERROR_USER_DISABLED("ERROR_USER_DISABLED", "User is disabled", HttpStatus.UNAUTHORIZED),
    BAD_CREDENTIALS("BAD_CREDENTIALS", "Username and / or password is incorrect", HttpStatus.UNAUTHORIZED),
    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND", "Username not found", HttpStatus.NOT_FOUND),
    INTERNAL_EXCEPTION("INTERNAL_EXCEPTION", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND", "", HttpStatus.NOT_FOUND);

    private final String code;
    private final String defaultMessage;
    private final HttpStatus status;

    ErrorCode(final String code, final String defaultMessage, final HttpStatus status){
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.status = status;
    }
}
