package com.bridgelabz.bookstoreapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CartData")
public class CartData {

    @Id
    @GeneratedValue
    private long cartId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne
    private UserData userData;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne
    private BookData bookData;

    private long quantity;

    private long totalPrice;

    public CartData(UserData userData, BookData bookData, long quantity) {
        this.userData = userData;
        this.bookData = bookData;
        this.quantity = quantity;
    }
}
