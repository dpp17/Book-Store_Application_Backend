package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;

public interface IUserBusinessLogics {

    public ResponseDTO registerUser(UserDTO userDTO);

    public String verifyAccount(String email, int otp);

    public String loginUser(String email,String password);

    String getForgottenPassword(String email);

    String resetPassword(String email, String password, String confirmPassword);

    ResponseDTO updateByEmail(UserDTO userDTO);

    ResponseDTO getUserByEmailId(String email);

    String deleteUserByEmailId(String email);

    String verifyUsingToken(String token);
}
