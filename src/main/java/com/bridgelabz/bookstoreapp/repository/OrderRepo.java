package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderData,Long> {
    @Query(value = "select * from book_store.order_data where order_id =:orderId",nativeQuery = true)
    OrderData getOrderById(long orderId);
}
