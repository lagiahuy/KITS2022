package com.bezkoder.springjwt.repository;


import com.bezkoder.springjwt.models.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {
    @Query("select odb from OrderBook odb where odb.user.id = ?1 ")
    List<OrderBook> orderBookByCodeUser(Long codeUser);

    @Query("select odb from OrderBook odb where odb.datetime < current_date and odb.status = 2")
    List<OrderBook> listOrderBookLate();

}
