package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.service.IBookBusinessLogics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookBusinessLogics iBookBusinessLogics;

    @PostMapping("/addBook")
    public ResponseDTO addBookToStore(@RequestBody BookDTO bookDTO){
        return iBookBusinessLogics.addBookToStore(bookDTO);
    }

    @GetMapping(value = {"/allBook"})
    public List<BookData> getAllBooks(){
        return iBookBusinessLogics.getAllBooks();
    }

    @GetMapping(value = "/getBookById")
    public ResponseDTO getBookById(@RequestParam long id) {
        return new ResponseDTO("Book Data With ID :: " + id, iBookBusinessLogics.getBookById(id));
    }

    @GetMapping(value = "/getBookByName")
    public List<BookData> getBookByName(@RequestParam String name) {
        return iBookBusinessLogics.getBookByName(name);
    }

//    @GetMapping(value = "/sortBook")
//    public List<BookData> sortHighToLow(@RequestParam int option) {
//        if(option == 1) {
//            return iBookBusinessLogics.sortByPriceHighToLOw();
//        }else if (option == 2) {
//            return iBookBusinessLogics.sortByPriceLowToHigh();
//        }else {
//            return iBookBusinessLogics.sortById();
//        }
//    }
//

    @PutMapping("/updateBook")
   public ResponseDTO updateBook(@RequestParam long id, @RequestBody BookDTO bookDTO){
        return iBookBusinessLogics.updateBook(id, bookDTO);
    }

    @DeleteMapping("/deleteBook")
    public String deleteUser(@RequestParam long id) {
        return iBookBusinessLogics.deleteBook(id);
    }

//    @PostMapping("/changePriceBook/{token}")
//    public String changePrice(@PathVariable("token") String token,@RequestParam long id, @RequestParam double price) {
//        return iBookBusinessLogics.changeBookPrice(token, id, price);
//    }
//
//
//    @PostMapping("/changeQuantityBook/{token}")
//    public String changeQuantity(@PathVariable("token") String token, @RequestParam long id, @RequestParam int quantity) {
//        return iBookBusinessLogics.changeBookQuantity(token, id, quantity);
//    }
}
