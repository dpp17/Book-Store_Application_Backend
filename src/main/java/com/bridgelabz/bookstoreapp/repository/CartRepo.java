package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.CartData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<CartData,Long> {

}
