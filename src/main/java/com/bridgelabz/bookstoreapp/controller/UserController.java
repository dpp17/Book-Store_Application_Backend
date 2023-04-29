package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.*;
import com.bridgelabz.bookstoreapp.service.IUserBusinessLogics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserBusinessLogics iUserServices;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("User Registered Successfully..", iUserServices.registerUser(userDTO)), HttpStatus.CREATED);
    }
    @PutMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestBody VerifyDTO verifyDTO){
        return new ResponseEntity<String>(iUserServices.verifyAccount(verifyDTO.email, verifyDTO.otp), HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        return new ResponseEntity<String>(iUserServices.loginUser(loginDTO.getEmail(),loginDTO.getPassword()), HttpStatus.ACCEPTED);
    }

    @PutMapping("/forgot")
    public String forgotPassword(@RequestParam String email){
        return iUserServices.getForgottenPassword(email);
    }

    @PutMapping("/reset")
    public String resetPassword(@RequestBody ForgotResetDTO forgotResetDTO){
        return iUserServices.resetPassword(forgotResetDTO.getToken(), forgotResetDTO.getPassword(), forgotResetDTO.getConfirmPassword());
    }

    @PutMapping("/updateUser")
    public ResponseEntity<ResponseDTO> updateByEmail(@RequestBody UserDTO userDTO){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("User Details Updated Successfully..", iUserServices.updateByEmail(userDTO)), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getUser")
    public ResponseEntity<ResponseDTO> getUserByEmailId(@RequestParam String email){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(":: User Detail :: ", iUserServices.getUserByEmailId(email)), HttpStatus.FOUND);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUserByEmailId(@RequestBody LoginDTO loginDTO){
        return iUserServices.deleteUserByEmailId(loginDTO.email);
    }

    @PutMapping("/verifyToken/{token}")
    public String verifyUsingToken(@PathVariable String token){
        return iUserServices.verifyUsingToken(token);
    }

}
