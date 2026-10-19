package com.example.tuto_spring_security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmailIgnoreCase(String email);
    Optional<User> findByEmailIgnoreCase(String email);
}
