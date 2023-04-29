package com.bridgelabz.bookstoreapp.dto;

import lombok.Data;

@Data
public class CartDTO {

    public long userId;
    public long bookId;
    public long quantity;

}
