package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
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
    @Column(name = "logo")
    private String bookLogoMultiPart;
    @Column(name = "price",nullable = false)
    private double bookPrice;
    @Column(name = "quantity",nullable = false)
    private int bookQuantity;

    public BookData(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.bookAuthor = bookDTO.getBookAuthor();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookLogoMultiPart = bookDTO.getBookLogoMultiPart();
        this.bookPrice = bookDTO.getBookPrice();
        this.bookQuantity = bookDTO.getBookQuantity();
    }
}
