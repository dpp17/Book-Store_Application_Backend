package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;

public interface IOrderBusinessLogics {

    ResponseDTO placeOrder(String token, OrderDTO orderDTO);
    String cancelOrder(String token, long orderId);
    ResponseDTO getAllOrders();
    ResponseDTO getAllOrderForUser(String token);
}
