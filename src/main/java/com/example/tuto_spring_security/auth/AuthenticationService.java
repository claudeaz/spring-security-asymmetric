package com.example.tuto_spring_security.auth;

import com.example.tuto_spring_security.auth.request.AuthenticationRequest;
import com.example.tuto_spring_security.auth.request.RefreshRequest;
import com.example.tuto_spring_security.auth.request.RegistrationRequest;
import com.example.tuto_spring_security.auth.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

    void register(RegistrationRequest request);

    AuthenticationResponse refreshToken(RefreshRequest request);
}
