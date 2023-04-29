package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.exception.BookIDNotFoundException;
import com.bridgelabz.bookstoreapp.exception.UserIDNotFoundException;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repository.BookRepo;
import com.bridgelabz.bookstoreapp.repository.UserRepo;
import com.bridgelabz.bookstoreapp.utility.JWTToken;
import org.hibernate.annotations.OrderBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookBusinessLogics implements IBookBusinessLogics{

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTToken jwtToken;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     :: Adding Book To Store ::                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public ResponseDTO addBookToStore(BookDTO bookDTO) {

        List<BookData> book = bookRepo.findAll().stream().filter(data-> data.getBookName().equals(bookDTO.getBookName())).collect(Collectors.toList());
        if(book.isEmpty()){
            BookData bookData = new BookData(bookDTO);
            bookRepo.save(bookData);
            return new ResponseDTO("Book Added Successfully..", bookData);
        }
        return new ResponseDTO("Book Already Present..", book);

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                  :: Get All Book From Store ::                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<BookData> getAllBooks() {
        return bookRepo.findAll();
    }

//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //                                   :: Sort Book By High Price ::                                    //
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////
//    @Override
//    public List<BookData> sortByPriceHighToLOw() {
//        return bookRepo.findAll().stream().sorted(Comparator.comparingDouble(BookData::getBookPrice).reversed()).collect(Collectors.toList());
//    }
//
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //                                     :: Sort Book By Low Price ::                                   //
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////
//    @Override
//    public List<BookData> sortByPriceLowToHigh() {
//        return bookRepo.findAll().stream().sorted(Comparator.comparingDouble(BookData::getBookPrice)).collect(Collectors.toList());
//    }
//
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //                                         :: Sort Book By ID ::                                      //
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////
//    @Override
//    public List<BookData> sortById() {
//        return bookRepo.findAll().stream().sorted(Comparator.comparingDouble(BookData::getId)).collect(Collectors.toList());
//    }
//

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       :: Getting Book By ID ::                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public BookData getBookById(long id) {
        return bookRepo.findById(id).orElseThrow(()-> new BookIDNotFoundException(" Invalid Book ID,Please Try With Different ID"));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     :: Getting Book By Name ::                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<BookData> getBookByName(String name) {
        BookData book = bookRepo.getBookByName(name);
        return bookRepo.findAll().stream().filter(data-> data.getBookName().equals(name)).collect(Collectors.toList());
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                   :: Getting Book Price By ID ::                                   //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public double getBookPriceById(long id) {
      BookData bookData = bookRepo.findById(id).orElseThrow(()-> new BookIDNotFoundException(" Invalid Book ID,Please Try With Different ID"));
      return bookData.getBookPrice();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     :: Update Book Details ::                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ResponseDTO updateBook(long id, BookDTO bookDTO) {
        BookData bookData = bookRepo.findById(id).orElseThrow(()-> new BookIDNotFoundException(" Invalid Book ID,Please Try With Different ID"));
        bookData.updateBookData(bookDTO);
        bookRepo.save(bookData);
        return new ResponseDTO("Book Updated SuccessFully..",bookData);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     :: Change Book Quantity ::                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
//    @Override
//    public String changeBookQuantity(String token, long bookId, int quantity) {
//        long idByToken = jwtToken.decodeToken(token);
//        UserData userData = userRepo.findById(idByToken).orElseThrow(()-> new UserIDNotFoundException(" User not found with token :: " + token));
//        BookData bookData = bookRepo.findById(bookId).orElseThrow(()-> new BookIDNotFoundException(" Invalid Book ID,Please Try With Different ID"));
//        bookData.setBookPrice(quantity);
//        bookRepo.save(bookData);
//        return "Successfully Changed The Quantity..";
//    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     :: Change Book By Price ::                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
//    @Override
//    public String changeBookPrice(String token, long bookId, double price) {
//        long idByToken = jwtToken.decodeToken(token);
//        UserData userData = userRepo.findById(idByToken).orElseThrow(()-> new UserIDNotFoundException(" User not found with token :: " + token));
//        BookData bookData = bookRepo.findById(bookId).orElseThrow(()-> new BookIDNotFoundException(" Invalid Book ID,Please Try With Different ID"));
//        bookData.setBookPrice(price);
//        bookRepo.save(bookData);
//        return "Successfully Changed The Price..";
//    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                   :: Delete Book From Store ::                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public String deleteBook(long id) {
        BookData book = bookRepo.findById(id).orElseThrow(()-> new BookIDNotFoundException(" Invalid Book ID,Please Try With Different ID"));
        bookRepo.deleteById(id);
        return "Book Details Deleted With ID :: " + id;
    }



}
