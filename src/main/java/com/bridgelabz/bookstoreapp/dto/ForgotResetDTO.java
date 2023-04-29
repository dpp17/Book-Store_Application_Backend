package com.bridgelabz.bookstoreapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ForgotResetDTO {

    @NotBlank(message = "Password Cannot be empty")
    @Pattern(regexp = "^[a-zA-Z_0-9]{1,}[!@#$%^&*][a-zA-Z_0-9]{1,}$", message = "password must have 1 Special Character")
    public String confirmPassword;

    @NotBlank(message = "Password Cannot be empty")
    @Pattern(regexp = "^[a-zA-Z_0-9]{1,}[!@#$%^&*][a-zA-Z_0-9]{1,}$", message = "password must have 1 Special Character")
    public String password;

    @NotNull(message = "Token Cannot be empty")
    public String token;
}
