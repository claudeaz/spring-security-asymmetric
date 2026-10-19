package com.example.tuto_spring_security.auth.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshRequest {
    private String refreshToken;

    //test
}
