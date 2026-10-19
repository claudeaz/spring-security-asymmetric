package com.example.tuto_spring_security.user;

import com.example.tuto_spring_security.user.request.ChangePasswordRequest;
import com.example.tuto_spring_security.user.request.ProfileUpdateRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "User ")
public class UserController {

    private final UserService userService;

    @PatchMapping("/me")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateProfile(
            @RequestBody @Valid ProfileUpdateRequest request,
            Authentication authentication
    ){
        userService.updateProfile(request, getUserId(authentication));
    }

    @PostMapping("/me/password")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void changePassword(
            @RequestBody @Valid ChangePasswordRequest request,
            Authentication authentication
    ){
        userService.changePassword(request, getUserId(authentication));
    }

    @PatchMapping("/me/deactivate")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deactivateAccount(
        Authentication authentication
    ){
        userService.deactivateAccount(getUserId(authentication));
    }

    @PatchMapping("/me/reactivate")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void reactivateAccount(
            Authentication authentication
    ){
        userService.reactivateAccount(getUserId(authentication));
    }

    @DeleteMapping("/me")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAccount(
            Authentication authentication
    ){
        userService.deleteAccount(getUserId(authentication));
    }

    private String getUserId(Authentication authentication) {
        return ((User) Objects.requireNonNull(authentication.getPrincipal())).getId();
    }
}
