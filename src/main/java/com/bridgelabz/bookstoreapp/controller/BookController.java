package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.service.IBookBusinessLogics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookBusinessLogics iBookBusinessLogics;

    @PostMapping("/addBook")
    public ResponseDTO addBookToStore(@RequestBody BookDTO bookDTO){
        return iBookBusinessLogics.addBookToStore(bookDTO);
    }

}
