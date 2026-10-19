package com.example.tuto_spring_security.auth.request;

import com.example.tuto_spring_security.validation.NonDisposableEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {

    @NotBlank(
            message = "VALIDATION.REGISTRATION.FIRSTNAME.NOT_BLANK"
    )
    @Size(
            min = 1,
            max = 50,
            message = "VALIDATION.REGISTRATION.FIRSTNAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$",
            message = "VALIDATION.REGISTRATION.FIRSTNAME.FORMAT"
    )
    @Schema(
            example = "Hien"
    )
    private String firstName;

    @NotBlank(
            message = "VALIDATION.REGISTRATION.LASTNAME.NOT_BLANK"
    )
    @Size(
            min = 1,
            max = 50,
            message = "VALIDATION.REGISTRATION.LASTNAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$",
            message = "VALIDATION.REGISTRATION.LASTNAME.FORMAT"
    )
    @Schema(
            example = "Claude"
    )
    private String lastName;

    @NotBlank(
            message = "VALIDATION.REGISTRATION.EMAIL.NOT_BLANK"
    )
    @Email(
            message = "VALIDATION.REGISTRATION.EMAIL.FORMAT"
    )
    @Schema(
            example = "claude@email.com"
    )
    @NonDisposableEmail(
            message = "VALIDATION.REGISTRATION.EMAIL.DISPOSABLE"
    )
    private String email;

    @NotBlank(
            message = "VALIDATION.REGISTRATION.PASSWORD.NOT_BLANK"
    )
    @Size(
            min = 8,
            max = 72,
            message = "VALIDATION.REGISTRATION.PASSWORD.SIZE"
    )
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W).*$",
            message = "VALIDATION.REGISTRATION.PASSWORD.WEAK"
    )
    @Schema(
            example = "<PASSWORD>"
    )
    private String password;

    @NotBlank(
            message = "VALIDATION.REGISTRATION.CONFIRM_PASSWORD.NOT_BLANK"
    )
    @Size(
            min = 8,
            max = 72,
            message = "VALIDATION.REGISTRATION.CONFIRM_PASSWORD.SIZE"
    )
    @Schema(
            example = "<PASSWORD>"
    )
    private String confirmedPassword;

    @NotBlank(
            message = "VALIDATION.REGISTRATION.PHONE.NOT_BLANK"
    )
    @Pattern(
            regexp = "^\\+?[0-9]{10,13}$",
            message = "VALIDATION.REGISTRATION.PHONE.FORMAT"
    )
    @Schema(
            example = "+923344556677"
    )
    private String phoneNumber;
}
