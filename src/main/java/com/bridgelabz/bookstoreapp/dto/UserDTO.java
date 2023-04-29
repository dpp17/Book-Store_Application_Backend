package com.bridgelabz.bookstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {

    @NotBlank(message = "firstName Cannot be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{1,}$", message = "firstName must start with capital")
    public String firstName;

    @NotBlank(message = "lastName Cannot be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{1,}$", message = "lastName must start with capital")
    public String lastName;

    @NotBlank(message = "UserName Cannot be empty")
    @Pattern(regexp = "^[a-zA-Z_0-9]{1,}[!@#$%^&*][a-zA-Z_0-9]{1,}$", message = "password must have 1 Special Character")
    public String password;

    @NotNull(message = "Email ID Cannot be empty")
    @Pattern(regexp = "^[a-z_0-9]{2,}@gmail.com$", message = "give email in format :: xxxxxxxx@gmail.com")
    public String email;

    @JsonFormat(pattern = "dd MM yyyy")
    @NotNull(message = "dob should not be empty")
    @PastOrPresent(message = "dob should be past or today's date")
    public LocalDate dob;

    @JsonFormat(pattern = "dd MM yyyy")
    @NotNull(message = "registeredDate should not be empty")
    @PastOrPresent(message = "registeredDate should be past or today's date")
    public LocalDate registeredDate;

}
