package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "BookData")
@Entity
@NoArgsConstructor
public class BookData {

    @Id
    @GeneratedValue
    @Column(name = "bookId", nullable = false)
    private long Id;
    @Column(name = "name",nullable = false)
    private String bookName;
    @Column(name = "author",nullable = false)
    private String bookAuthor;
    @Column(name = "description")
    private String bookDescription;
    @Column(name = "price",nullable = false)
    private double bookPrice;
    @Column(name = "quantity",nullable = false)
    private int bookQuantity;

    public void updateBookData(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.bookAuthor = bookDTO.getBookAuthor();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookPrice = bookDTO.getBookPrice();
        this.bookQuantity = bookDTO.getBookQuantity();
    }

    public BookData(BookDTO bookDTO) {
        this.updateBookData(bookDTO);
    }
}
