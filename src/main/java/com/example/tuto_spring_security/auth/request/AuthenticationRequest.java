package com.example.tuto_spring_security.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    @NotBlank(message = "VALIDATION.REGISTRATION.EMAIL.NOT_BLANK")
    @Email(message = "VALIDATION.REGISTRATION.EMAIL.FORMAT")
    @Schema(example = "claude@email.com")
    private String username;

    @NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.NOT_BLANK")
    @Schema(example = "<PASSWORD>")
    private String password;

    //test
}
