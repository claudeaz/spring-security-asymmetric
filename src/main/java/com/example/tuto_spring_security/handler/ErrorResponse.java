package com.example.tuto_spring_security.handler;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorResponse {
    private String code;
    private String message;
    private List<ValidationError> validationErrors;


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class ValidationError {
        private String field;
        private String code;
        private String message;
    }
}
