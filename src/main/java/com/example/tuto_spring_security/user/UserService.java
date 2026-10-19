package com.example.tuto_spring_security.user;

import com.example.tuto_spring_security.user.request.ChangePasswordRequest;
import com.example.tuto_spring_security.user.request.ProfileUpdateRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void updateProfile(ProfileUpdateRequest profileUpdateRequest, String userId);

    void changePassword(ChangePasswordRequest changePasswordRequest, String userId);

    void deactivateAccount(String userId);

    void reactivateAccount(String userId);

    void deleteAccount(String userId);
}
