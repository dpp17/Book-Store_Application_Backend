package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;

public interface IBookBusinessLogics {
    ResponseDTO addBookToStore(BookDTO bookDTO);
}
