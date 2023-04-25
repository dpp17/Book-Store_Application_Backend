package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookBusinessLogics implements IBookBusinessLogics{

    @Autowired
    private BookRepo bookRepo;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     :: Adding Book To Store ::                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public ResponseDTO addBookToStore(BookDTO bookDTO) {
        BookData bookData = new BookData(bookDTO);
        bookRepo.save(bookData);
        return new ResponseDTO("Book Added Successfully..", bookData);
    }


}
