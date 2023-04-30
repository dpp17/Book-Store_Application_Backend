package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.exception.UserIDNotFoundException;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.CartData;
import com.bridgelabz.bookstoreapp.model.OrderData;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repository.BookRepo;
import com.bridgelabz.bookstoreapp.repository.CartRepo;
import com.bridgelabz.bookstoreapp.repository.OrderRepo;
import com.bridgelabz.bookstoreapp.repository.UserRepo;
import com.bridgelabz.bookstoreapp.utility.EmailServices;
import com.bridgelabz.bookstoreapp.utility.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderBusinessLogics implements IOrderBusinessLogics{

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private JWTToken jwtToken;
    @Autowired
    private EmailServices emailServices;

    @Override
    public ResponseDTO placeOrder(String token, OrderDTO orderDTO) {
        long userId = jwtToken.decodeToken(token);
        UserData userData = userRepo.findById(userId).orElseThrow(() -> new UserIDNotFoundException("User ID :: " + userId + " not Registered.."));
        List<BookData> bookList = cartRepo.findAll().stream().filter(data-> data.getUserData().getUserId() == userId).map(CartData::getBookData).toList();
//        System.out.println(bookList.toString());
        List<OrderData> orderDataList = new ArrayList<>();      // to return all orders if multiple orders are present
        double price = 0;
        OrderData orderData = null;
        for (int i = 0; i < bookList.size(); i++) {
            price = price + bookList.get(i).getBookQuantity() * bookList.get(i).getBookPrice();
            orderData = new OrderData(orderDTO, userData,bookList.get(i), bookList.get(i).getBookPrice()*bookList.get(i).getBookQuantity(), bookList.get(i).getBookQuantity());
            orderRepo.save(orderData);
           orderDataList.add(orderData);
        }
        emailServices.sendEmail(userData.getEmail(), "Order Details", "Hello " + userData.getFirstName() + ","
                + "\n\n Your order has been placed successfully..\n\n Your Order details :: " + orderDataList + "\n\n Regards,\n\n People's Mart");
        return new ResponseDTO("Order Placed SuccessFully..\n\n Total order Price :: " + price, orderDataList);
    }

    @Override
    public String cancelOrder(String token, long orderId) {
        long userId = jwtToken.decodeToken(token);
        OrderData orderData = orderRepo.getOrderById(orderId);
        orderData.setCancel(true);
        orderRepo.save(orderData);
        emailServices.sendEmail(orderData.getUserId().getEmail(), "Order Details", "Hello " + orderData.getUserId().getFirstName() + ","
                + "\n\n Your order is cancelled successfully..\n\n Your cancelled Order details :: " + orderData + "\n\n Regards,\n\n People's Mart");
        return "Order Cancelled";
    }

    @Override
    public ResponseDTO getAllOrders() {
        List<OrderData> orderDataList = orderRepo.findAll();
        return new ResponseDTO("List of All Orders..", orderDataList);
    }

    @Override
    public ResponseDTO getAllOrderForUser(String token) {
        long userId = jwtToken.decodeToken(token);
        List<OrderData> orderDataList = orderRepo.findAll().stream().filter(data-> data.getUserId().getUserId() == userId).collect(Collectors.toList());
        return new ResponseDTO("List of Orders By UserID :: " + userId, orderDataList);
    }
}
