package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.BookData;

import java.util.List;

public interface IBookBusinessLogics {
    ResponseDTO addBookToStore(BookDTO bookDTO);
    List<BookData> getAllBooks();
//
//    List<BookData> sortByPriceHighToLOw();
//
//    List<BookData> sortByPriceLowToHigh();
//
//    List<BookData> sortById();

    BookData getBookById(long id);

    List<BookData> getBookByName(String name);

    double getBookPriceById(long id);

    ResponseDTO updateBook(long id, BookDTO bookDTO);

//    String changeBookQuantity(String token, long id, int quantity);
//
//    String changeBookPrice(String token, long id, double price);

    String deleteBook(long id);
}
