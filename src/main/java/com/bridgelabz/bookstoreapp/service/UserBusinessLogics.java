package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import com.bridgelabz.bookstoreapp.exception.UserIDNotFoundException;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repository.UserRepo;
import com.bridgelabz.bookstoreapp.utility.EmailServices;
import com.bridgelabz.bookstoreapp.utility.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class UserBusinessLogics implements IUserBusinessLogics{

@Autowired
private UserRepo userRepo;
@Autowired
private EmailServices emailService;

@Autowired
private JWTToken jwtToken;

private UserData checkIfUserExist(String email){
    return userRepo.getUserIDByEmail(email);
}

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                    :: UserBook - Registration ::                                   //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public ResponseDTO registerUser(UserDTO userDTO) {
        UserData data = checkIfUserExist(userDTO.getEmail());

        if(data == null){
            Random random = new Random();
            UserData registerNewUser = new UserData(userDTO);
            registerNewUser.setOtp((random.nextInt(9000)+999));
            userRepo.save(registerNewUser);
            emailService.sendEmail(userDTO.getEmail(),"User DataBase Account Verification",
                    "Hey... " + (userDTO.getFirstName()) +
                            "\n\n Your OTP for VERIFICATION :: "+registerNewUser.getOtp()+
                            "\n\n Click link to verify your account :: http://localhost:8080/userBook/verify");
            return new ResponseDTO("User_Information",registerNewUser);
        }
        return new ResponseDTO("User with same Email ID already exist..", data);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                   :: UserBook - Verify Account ::                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public String verifyAccount(String email, int otp) {
        UserData data = checkIfUserExist(email);
        if(null != data){
            if(data.getOtp() == otp){
                data.setVerify(true);
                userRepo.save(data);
                emailService.sendEmail(email,"Account verified successful",
                    "Hello Sir/Mam, \n\n Your Account is successfully verified !!! ");
            return "Account Verified successfully";
            }
        }
        return " User not found with email id :: " + email;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                   :: UserBook - User Login ::                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String loginUser(String email,String password) {
        UserData data = checkIfUserExist(email);
        if(null != data){
                if(data.getPassword().equals(password)){
                    String token = jwtToken.createToken(data.getUserId());
                    return ":: Welcome " + data.getFirstName() + " " + data.getLastName() + " ::" + "\n\n" + token;
                }
        }
            return "!!!!!!!!!!!! User with Email ID :: " + email + " doesn't exists !!!!!!!!!!!!!!";
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                  :: UserBook - forgot password ::                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String getForgottenPassword(String email) {
        UserData data = checkIfUserExist(email);
        if(null != data) {
            emailService.sendEmail(email,"Create New Password",
                    "Hello Sir/Mam, \n\n Create new password from the link :: http://localhost:8080/userBook/reset" );
            return "Create new password from your registered email id. A link has been sent :: http://localhost:8080/userBook/reset";
        }
        return "!!!!!!!!!!!! User with Email ID :: " + email + " doesn't exists !!!!!!!!!!!!!!";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                   :: UserBook - Reset password ::                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String resetPassword(String email, String password, String confirmPassword) {
        if(password.equals(confirmPassword)){
            UserData data = checkIfUserExist(email);
            if(null != data) {
                data.setPassword(confirmPassword);
                userRepo.save(data);
                return " Your Password Updated Successfully !!";
            }
            return "User with Email ID : " + email + " doesn't exist..";
        }
        return "Entered Password and Confirm Password doesn't match..";
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                         :: UserBook - Update User Details Using Email ID ::                        //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public ResponseDTO updateByEmail(UserDTO userDTO) {
        UserData data = checkIfUserExist(userDTO.getEmail());
        if(null != data){
            data.updatedDate(userDTO);
            data.setUpdatedDate(LocalDate.now());
            userRepo.save(data);
            return new ResponseDTO("User Details Updated Successfully....", data);
        }
        return new ResponseDTO("!!!!!!!!!!!! User with Email ID :: " + userDTO.getEmail() + " doesn't exists !!!!!!!!!!!!!!", null);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                         :: UserBook - Get User Details Using Email ID ::                        //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public ResponseDTO getUserByEmailId(String email) {
        UserData data = checkIfUserExist(email);
        if(null != data){
            return new ResponseDTO("Details :: ", data);
        }
        return new ResponseDTO("!!!!!!!!!!!! User with Email ID :: " + email + " doesn't exists !!!!!!!!!!!!!!", null);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                         :: UserBook - Delete User Details Using Email ID ::                        //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String deleteUserByEmailId(String email) {
        UserData data = checkIfUserExist(email);
        if(null != data){
            userRepo.deleteById(data.getUserId());
            return "User with Email ID :: " + email + " successfully deleted..";
        }
        return "!!!!!!!!!!!! User with Email ID :: " + email + " doesn't exists !!!!!!!!!!!!!!";
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                 :: UserBook - Verify Using Token ::                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public String verifyUsingToken(String token) {
        long id = jwtToken.decodeToken(token);
        UserData data = userRepo.findById(id).orElseThrow(()-> new UserIDNotFoundException(" User not found with token :: " + token));;
        if(data.isVerify()){
            return " Data is already verified..";
        }
        data.setVerify(true);
        userRepo.save(data);
        return " Data verified Successfully..";
    }


}


