package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.*;
import com.bridgelabz.bookstoreapp.service.IUserBusinessLogics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserBusinessLogics iUserServices;

    @PostMapping("/register")
    public ResponseDTO registerUser(@RequestBody UserDTO userDTO){
        return iUserServices.registerUser(userDTO);
    }
    @PutMapping("/verify")
    public String verifyUser(@RequestBody VerifyDTO verifyDTO){
        return iUserServices.verifyAccount(verifyDTO.email, verifyDTO.otp);
    }

    @GetMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO){
        return iUserServices.loginUser(loginDTO.getEmail(),loginDTO.getPassword());
    }

    @PutMapping("/forgot")
    public String forgotPassword(@RequestParam String email){
        return iUserServices.getForgottenPassword(email);
    }

    @PutMapping("/reset")
    public String resetPassword(@RequestBody ForgotResetDTO forgotResetDTO){
        return iUserServices.resetPassword(forgotResetDTO.getEmail(), forgotResetDTO.getPassword(), forgotResetDTO.getConfirmPassword());
    }

    @PutMapping("/updateUser")
    public ResponseDTO updateByEmail(@RequestBody UserDTO userDTO){
        return iUserServices.updateByEmail(userDTO);
    }

    @GetMapping("/getUser")
    public ResponseDTO getUserByEmailId(@RequestBody UserDTO userDTO){
        return iUserServices.getUserByEmailId(userDTO.email);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUserByEmailId(@RequestBody LoginDTO loginDTO){
        return iUserServices.deleteUserByEmailId(loginDTO.email);
    }

}
