package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<BookData,Long> {

    @Query(value = "select * from book_store.user_data where name = :name",nativeQuery = true)
    BookData getBookByName(String name);
}
