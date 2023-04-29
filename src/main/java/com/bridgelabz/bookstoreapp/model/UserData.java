package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "UserData")
@Data
@NoArgsConstructor
public class UserData {

    @Id
    @GeneratedValue
    private long userId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private LocalDate registeredDate;
    private LocalDate updatedDate;
    private String email;
    private String password;
    private int otp;
    private boolean verify;

    public void updatedDate(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.dob = userDTO.getDob();
        this.registeredDate = LocalDate.now();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
    }

    public UserData(UserDTO userDTO) {
        this.updatedDate(userDTO);
    }

}
