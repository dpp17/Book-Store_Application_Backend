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

import java.util.Random;

@Service
public class UserBusinessLogics implements IUserBusinessLogics{

@Autowired
private UserRepo userRepo;
@Autowired
private EmailServices emailService;

@Autowired
private JWTToken jwtToken;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                    :: UserBook - Registration ::                                   //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public ResponseDTO registerUser(UserDTO userDTO) {
        Random random = new Random();
        UserData registerNewUser = new UserData(userDTO);
        registerNewUser.setOtp((random.nextInt(9000)+999));

        String isEmailPresent = userRepo.getEmailByEmail(registerNewUser.getEmail());

        if(isEmailPresent == null){
            userRepo.save(registerNewUser);
            emailService.sendEmail(userDTO.getEmail(),"User DataBase Account Verification",
                    "Hey... "+(userDTO.getFirstName())+"\n\n Your OTP for VERIFICATION :: "+registerNewUser.getOtp()+
                    "\n\n Click link to verify your account :: http://localhost:8080/userBook/verify");
            return new ResponseDTO("User_Information",registerNewUser);
        }
        return new ResponseDTO("User with same Email ID already exist..",userRepo.getEmailByEmail(userDTO.getEmail()));
    }
// ==>>  check if email is present or not

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                   :: UserBook - Verify Account ::                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public String verifyAccount(String email, int otp) {

        String idStr = userRepo.getUserIDByEmail(email);
        if(null != idStr){
            long id = Integer.parseInt(idStr);
            UserData data = userRepo.findById(id).orElseThrow(()-> new UserIDNotFoundException(" User not found with email id :: " + email));
            if(data.getOtp() == otp){
                data.setVerify(true);
                userRepo.deleteById(id);
                userRepo.save(data);
                emailService.sendEmail(email,"Account verified successful",
                    "Hello Sir/Mam, \n\n Your Account is successfully verified !!! ");
            return "Account Verified successfully";
            }
        }
        return "Invalid OTP";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                   :: UserBook - User Login ::                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String loginUser(String email,String password) {
        String idStr = userRepo.getUserIDByEmail(email);
        if(null != idStr){
                long id = Integer.parseInt(idStr);
                UserData data = userRepo.findById(id).orElseThrow(()-> new UserIDNotFoundException(" User not found with email id :: " + email));
                if(data.getPassword().equals(password)){
                    String token = jwtToken.createToken(id);
                    return ":: Welcome " + data.getFirstName() + " " + data.getLastName() + " ::";
                }
        }
            return "!!!!!!!!!!!! User with Email ID :: " + email + " doesn't exists !!!!!!!!!!!!!!";
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                  :: UserBook - forgot password ::                                  //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String getForgottenPassword(String email) {
        String idStr = userRepo.getUserIDByEmail(email);
        if(null != idStr) {
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
            String idStr = userRepo.getUserIDByEmail(email);
            if(null != idStr) {
                long id = Integer.parseInt(idStr);
                UserData data = userRepo.findById(id).orElseThrow(()-> new UserIDNotFoundException(" User not found with email id :: " + email));
                data.setPassword(confirmPassword);
                userRepo.deleteById(id);
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
        String idStr = userRepo.getUserIDByEmail(userDTO.getEmail());
        if(null != idStr){
            long id = Integer.parseInt(idStr);
            UserData data = userRepo.findById(id).orElseThrow(()-> new UserIDNotFoundException(" User not found with email id :: " + userDTO.getEmail()));


            data.setFirstName(userDTO.getFirstName());
            data.setEmail(userDTO.getEmail());
            data.setPassword(userDTO.getPassword());


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
        String idStr = userRepo.getUserIDByEmail(email);
        if(null != idStr){
            long id = Integer.parseInt(idStr);
            UserData data = userRepo.findById(id).orElseThrow(()-> new UserIDNotFoundException(" User not found with email id :: " + email));
            return new ResponseDTO("Details :: ", data);
        }
        return new ResponseDTO("!!!!!!!!!!!! User with Email ID :: " + email + " doesn't exists !!!!!!!!!!!!!!", null);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                         :: UserBook - Delete User Details Using Email ID ::                        //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String deleteUserByEmailId(String email) {
        String idStr = userRepo.getUserIDByEmail(email);
        if(null != idStr){
            long id = Integer.parseInt(idStr);
            userRepo.deleteById(id);
            return "User with Email ID :: " + email + " successfully deleted..";
        }
        return "!!!!!!!!!!!! User with Email ID :: " + email + " doesn't exists !!!!!!!!!!!!!!";
    }


}


