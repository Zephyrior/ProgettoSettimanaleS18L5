package it.epicode.ProgettoSettimanaleS18L5.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
