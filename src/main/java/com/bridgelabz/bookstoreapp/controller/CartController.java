package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.CartData;
import com.bridgelabz.bookstoreapp.service.ICartBusinessLogics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartBusinessLogics iCartBusinessLogics;

    @PostMapping("/addToCart")
    public ResponseEntity<ResponseDTO> addItemsToCart(@RequestBody CartDTO cartDTO){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Book Successfully Added", iCartBusinessLogics.addCart(cartDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/removeCartById")
    public String removeItemFromCartById(@RequestParam long id){
        return iCartBusinessLogics.removeFromCart(id);
    }

    @DeleteMapping("/removeAllCart/{token}")
    public String removeAllCarts(@PathVariable String token){
        return iCartBusinessLogics.removeAllCarts(token);
    }

    @PutMapping("/updateCart/{token}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable String token,@RequestParam long cartId,@RequestParam long quantity){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Updated Details", iCartBusinessLogics.updateQuantity(token,cartId,quantity)), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllCart/{token}")
    public List<CartData> findAllCarts(@PathVariable String token){
        return iCartBusinessLogics.findAllCarts(token);
    }



}
