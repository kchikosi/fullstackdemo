package com.dev.fullstackdemo.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
}
