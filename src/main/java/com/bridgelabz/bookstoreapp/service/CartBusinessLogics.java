package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.exception.BookIDNotFoundException;
import com.bridgelabz.bookstoreapp.exception.UserIDNotFoundException;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.CartData;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repository.BookRepo;
import com.bridgelabz.bookstoreapp.repository.CartRepo;
import com.bridgelabz.bookstoreapp.repository.UserRepo;
import com.bridgelabz.bookstoreapp.utility.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartBusinessLogics implements ICartBusinessLogics{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private JWTToken jwtToken;

    @Override
    public ResponseDTO addCart(CartDTO cartDTO) {
        CartData cartItem = new CartData(userRepo.findById(cartDTO.getUserId()).orElseThrow(()-> new UserIDNotFoundException(" User not found with UserID :: " + cartDTO.getUserId())), bookRepo.findById(cartDTO.getBookId()).orElseThrow(()-> new BookIDNotFoundException("User not Found with BookID :: " + cartDTO.getBookId())), cartDTO.getQuantity());
        cartRepo.save(cartItem);
        return new ResponseDTO("Added", cartDTO);
    }

    @Override
    public String removeFromCart(long cartId) {
        if(cartRepo.findById(cartId).isPresent()){
            cartRepo.deleteById(cartId);
            return "Cart Deleted Successfully..";
        }
        return "Cart ID :: " + cartId + " Not Found..";
    }

    @Override
    public String removeAllCarts(String token) {
        long userId = jwtToken.decodeToken(token);
        if(userRepo.findById(userId).isPresent()){
            cartRepo.deleteAll();
            return "All Carts Deleted Successfully..";
        }
        return "User with token :: " + token + " Not Found..";
    }

    @Override
    public ResponseDTO updateQuantity(String token, long cartId, long quantity) {
        long userId = jwtToken.decodeToken(token);
        CartData cartData = cartRepo.findById(cartId).orElseThrow();
        if(cartData.getUserData().getUserId() == userId){
            cartData.setQuantity(quantity);
            cartRepo.save(cartData);
            return new ResponseDTO("Updated Successfully..",cartData);
        }
        return new ResponseDTO("Error while Updating",null);
    }

    @Override
    public List<CartData> findCartItemsByToken(String token) {
        long userId = jwtToken.decodeToken(token);
        return cartRepo.findAll().stream().filter(data-> data.getUserData().getUserId() == userId).toList();
    }

    @Override
    public List<CartData> findAllCarts() {
        return cartRepo.findAll();
    }
}
