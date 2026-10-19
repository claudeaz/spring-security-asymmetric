package com.example.tuto_spring_security.auth;

import com.example.tuto_spring_security.auth.request.AuthenticationRequest;
import com.example.tuto_spring_security.auth.request.RefreshRequest;
import com.example.tuto_spring_security.auth.request.RegistrationRequest;
import com.example.tuto_spring_security.auth.response.AuthenticationResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(this.authenticationService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody RegistrationRequest request
    ){
        this.authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(
            @RequestBody RefreshRequest request
    ){
        return ResponseEntity.ok(this.authenticationService.refreshToken(request));
    }
}
