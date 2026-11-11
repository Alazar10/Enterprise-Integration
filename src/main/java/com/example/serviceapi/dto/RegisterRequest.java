package com.example.serviceapi.dto;

import com.example.serviceapi.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "Username cannot be empty") 
    String username,

    @NotBlank(message = "Password cannot be empty") 
    @Size(min = 6, message = "Password must be at least 6 characters long") 
    String password,

    @NotNull(message = "Role must be specified") 
    Role role
) {}