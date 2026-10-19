package com.example.tuto_spring_security.auth.impl;

import com.example.tuto_spring_security.auth.AuthenticationService;
import com.example.tuto_spring_security.auth.request.AuthenticationRequest;
import com.example.tuto_spring_security.auth.request.RefreshRequest;
import com.example.tuto_spring_security.auth.request.RegistrationRequest;
import com.example.tuto_spring_security.auth.response.AuthenticationResponse;
import com.example.tuto_spring_security.exception.BusinessException;
import com.example.tuto_spring_security.exception.ErrorCode;
import com.example.tuto_spring_security.role.Role;
import com.example.tuto_spring_security.role.RoleRepository;
import com.example.tuto_spring_security.security.JwtService;
import com.example.tuto_spring_security.user.User;
import com.example.tuto_spring_security.user.UserMapper;
import com.example.tuto_spring_security.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        final Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        final User user = (User) auth.getPrincipal();
            final String accessToken = jwtService.generateAccessToken(user.getUsername());
            final String refreshToken = jwtService.generateRefreshToken(user.getUsername());
            final String tokenType = "Bearer";


        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(tokenType)
                .build();
    }

    @Override
    @Transactional
    public void register(RegistrationRequest request) {
        checkUserEmail(request.getEmail());
        checkUserNumber(request.getPhoneNumber());
        checkPassword(request.getPassword(), request.getConfirmedPassword());

        final Role userRole = this.roleRepository.findByName("ROLE_USER")
                .orElseThrow(()-> new EntityNotFoundException("Role user does not exist"));
        final List<Role> roles = new ArrayList<>();
        roles.add(userRole);

        final User user = this.userMapper.toUser(request);
        user.setRoles(roles);
        log.debug("Saving user {} ", user);
        this.userRepository.save(user);

        //In case the cascading type property was not defined in the Many To Many relationship between USER -- ROLE
        final List<User> users = new ArrayList<>();
        users.add(user);
        userRole.setUsers(users);
        this.roleRepository.save(userRole);
    }

    @Override
    public AuthenticationResponse refreshToken(final RefreshRequest request) {
        final String newAccessToken = this.jwtService.refreshAccessToken(request.getRefreshToken());
        final String token_type = "Bearer";
        return AuthenticationResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(request.getRefreshToken())
                .tokenType(token_type)
                .build();
    }

    private void checkPassword(String password, String confirmedPassword) {
        if (!password.equals(confirmedPassword)){
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }
    }

    private void checkUserNumber(String phoneNumber) {
        boolean userExist = this.userRepository.existsByPhoneNumber(phoneNumber);
        if (userExist){
            throw new BusinessException(ErrorCode.PHONE_NUMBER_ALREADY_EXIST);
        }
    }

    private void checkUserEmail(String email) {
        boolean userExist = this.userRepository.existsByEmailIgnoreCase(email);
        if (userExist){
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXIST);
        }
    }

    // test
}
