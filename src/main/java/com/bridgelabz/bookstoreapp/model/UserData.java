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
    private String kyc;
    private LocalDate dob;
    private LocalDate registeredDate;
    private LocalDate updatedDate;
    private String email;
    private String password;
    private int otp;
    private boolean verify;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;

    public UserData(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.kyc = userDTO.getKyc();
        this.dob = userDTO.getDob();
        this.registeredDate = userDTO.getRegisteredDate();
        this.updatedDate = userDTO.getUpdatedDate();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.purchaseDate = userDTO.getPurchaseDate();
        this.expiryDate = userDTO.getExpiryDate();
    }
}
