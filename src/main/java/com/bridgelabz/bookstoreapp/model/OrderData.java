package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "OrderData")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderData {
    @Id
    @GeneratedValue
    private long orderId;
    private LocalDate orderDate;
    private double totalPrice;
    private long quantity;
    private String address;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne
    private UserData userId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne
    private BookData bookId;
    private boolean cancel;


    public OrderData(OrderDTO orderDTO, UserData userId, BookData bookId, double totalPrice, long quantity) {
        this.quantity=quantity;
        this.bookId = bookId;
        this.userId = userId;
        this.orderDate = LocalDate.now();
        this.address=orderDTO.getAddress();
        this.totalPrice = totalPrice;
    }

}
