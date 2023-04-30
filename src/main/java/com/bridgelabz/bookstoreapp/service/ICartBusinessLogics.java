package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.CartData;

import java.util.List;

public interface ICartBusinessLogics {
    ResponseDTO addCart(CartDTO cartDTO);

    String removeFromCart(long cartId);

    String removeAllCarts(String token);

    ResponseDTO updateQuantity(String token, long cartId, long quantity);

    List<CartData> findCartItemsByToken(String token);

    List<CartData> findAllCarts();
}
