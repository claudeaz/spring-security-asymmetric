package com.example.tuto_spring_security.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProfileUpdateRequest {
    @NotBlank(
            message = "VALIDATION.PROFILE_UPDATE.FIRSTNAME.NOT_BLANK"
    )
    @Size(
            min = 5,
            max = 50,
            message = "VALIDATION.PROFILE_UPDATE.FIRSTNAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$",
            message = "VALIDATION.PROFILE_UPDATE.FIRSTNAME.FORMAT"
    )
    @Schema(
            example = "Claude"
    )
    private String firstName;

    @NotBlank(
            message = "VALIDATION.PROFILE_UPDATE.LASTNAME.NOT_BLANK"
    )
    @Size(
            min = 5,
            max = 50,
            message = "VALIDATION.PROFILE_UPDATE.LASTNAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$",
            message = "VALIDATION.PROFILE_UPDATE.LASTNAME.FORMAT"
    )
    @Schema(
            example = "Claude"
    )
    private String lastName;


    private LocalDate dateOfBirth;
}
