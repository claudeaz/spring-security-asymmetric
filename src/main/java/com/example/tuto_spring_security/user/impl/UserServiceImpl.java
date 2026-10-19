package com.example.tuto_spring_security.user.impl;

import com.example.tuto_spring_security.exception.BusinessException;
import com.example.tuto_spring_security.exception.ErrorCode;
import com.example.tuto_spring_security.user.User;
import com.example.tuto_spring_security.user.UserMapper;
import com.example.tuto_spring_security.user.UserRepository;
import com.example.tuto_spring_security.user.UserService;
import com.example.tuto_spring_security.user.request.ChangePasswordRequest;
import com.example.tuto_spring_security.user.request.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String userEmail) throws UsernameNotFoundException {
        return this.userRepository.findByEmailIgnoreCase(userEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with userEmail: " + userEmail));
    }

    @Override
    public void updateProfile(ProfileUpdateRequest profileUpdateRequest, String userId) {
        User savedUser = userRepository.findById(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));
        this.userMapper.mergeUserInfo(savedUser, profileUpdateRequest);
        this.userRepository.save(savedUser);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest, String userId) {
        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmNewPassword())){
            throw new BusinessException(ErrorCode.CHANGE_PASSWORD_MISMATCH);
        }
        User savedUser = userRepository.findById(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));
        if(!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), savedUser.getPassword())){
            throw new BusinessException(ErrorCode.INVALID_CURRENT_PASSWORD);
        }
        final String newEncodedPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
        savedUser.setPassword(newEncodedPassword);
        userRepository.save(savedUser);
    }

    @Override
    public void deactivateAccount(String userId) {
        User savedUser = userRepository.findById(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));
        if(!savedUser.isEnabled()){
            throw new BusinessException(ErrorCode.ACCOUNT_ALREADY_DEACTIVATED);
        }
        savedUser.setEnable(false);
        userRepository.save(savedUser);
    }

    @Override
    public void reactivateAccount(String userId) {
        User savedUser = userRepository.findById(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));
        if(savedUser.isEnabled()){
            throw new BusinessException(ErrorCode.ACCOUNT_ALREADY_DEACTIVATED);
        }
        savedUser.setEnable(true);
        userRepository.save(savedUser);
    }

    @Override
    public void deleteAccount(String userId) {
        User savedUser = userRepository.findById(userId)
                .orElseThrow(()-> new BusinessException(ErrorCode.USER_NOT_FOUND, userId));
        userRepository.deleteById(userId);
    }

}
