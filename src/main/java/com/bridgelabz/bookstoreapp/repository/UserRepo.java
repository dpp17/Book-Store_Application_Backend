package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserData,Long> {
    @Query(value = "select * from book_store.user_data where email = :email",nativeQuery = true)
    UserData getUserIDByEmail(String email);
}
