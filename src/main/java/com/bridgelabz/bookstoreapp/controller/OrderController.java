package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.service.IOrderBusinessLogics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderCart")
public class OrderController {

    @Autowired
    private IOrderBusinessLogics orderBusinessLogics;

    @PostMapping("/placeOrder/{token}")
    public ResponseEntity<ResponseDTO> placeOrder(@PathVariable String token,@RequestBody OrderDTO orderDTO){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Order Details..", orderBusinessLogics.placeOrder(token,orderDTO)), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<ResponseDTO> getAllOrdersList(){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(":: All-Order ::", orderBusinessLogics.getAllOrders()), HttpStatus.FOUND);
    }

    @PutMapping("/cancelOrder/{token}")
    public String cancelOrder(@PathVariable String token,@RequestParam long orderId){
        return orderBusinessLogics.cancelOrder(token,orderId);
    }

    @GetMapping("/getOrdersByUserToken/{token}")
    public  ResponseEntity<ResponseDTO> getAllOrderForUser(@PathVariable String token){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Your Order Details..",orderBusinessLogics.getAllOrderForUser(token)), HttpStatus.FOUND);
    }



}
