package com.example.tuto_spring_security.user;

import com.example.tuto_spring_security.auth.request.RegistrationRequest;
import com.example.tuto_spring_security.user.request.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder encoder;

    public void mergeUserInfo(User savedUser, ProfileUpdateRequest profileUpdateRequest) {
        if(StringUtils.isNotBlank(profileUpdateRequest.getFirstName())
                && !savedUser.getFirstName().equals(profileUpdateRequest.getFirstName())
        ){
            savedUser.setFirstName(profileUpdateRequest.getFirstName());
        }

        if(StringUtils.isNotBlank(profileUpdateRequest.getLastName())
                && !savedUser.getLastName().equals(profileUpdateRequest.getLastName())
        ){
            savedUser.setLastName(profileUpdateRequest.getLastName());
        }

        if(profileUpdateRequest.getDateOfBirth() != null
                && !savedUser.getDateOfBirth().equals(profileUpdateRequest.getDateOfBirth())
        ){
            savedUser.setDateOfBirth(profileUpdateRequest.getDateOfBirth());
        }
    }

    public User toUser(RegistrationRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(encoder.encode(request.getPassword()))
                .enable(true)
                .locked(false)
                .credentialsExpired(false)
                .phoneVerified(false)
                .build();
    }
}
