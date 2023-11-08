package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.DetailBook;
import com.bezkoder.springjwt.models.OrderBook;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.models.UserStatus;
import com.bezkoder.springjwt.repository.DetailBookRepository;
import com.bezkoder.springjwt.repository.OrderBookRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class OrderBookController {
    @Autowired
    private OrderBookRepository orderBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DetailBookRepository detailBookRepository;

    @Autowired
    private UserStatusRepository userStatusRepository;

    @GetMapping("/oderBook")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<OrderBook> getAllOrderBooks() {
        return orderBookRepository.findAll();
    }

    @GetMapping("/oderBook/{id}")
    public List<OrderBook> getAllOrderBooksByCodeUser(@PathVariable Long id) {
        return orderBookRepository.orderBookByCodeUser(id);
    }
    @GetMapping("/oderBookOff")
    public OrderBook createOrderBookOff(@RequestParam Long idUser, @RequestParam Long idBook, @RequestParam Long idBranch) {
        OrderBook orderBook = createOrder(idUser,idBook,idBranch);
        if(orderBook == null){
            return orderBook;
        }
        orderBook.setStatus(2);
        return orderBookRepository.save(orderBook);
    }
    @GetMapping("/oderBookOnl")
    public OrderBook createOrderBookOnl(@RequestParam Long idUser, @RequestParam Long idBook, @RequestParam Long idBranch) {
        OrderBook orderBook = createOrder(idUser,idBook,idBranch);
        if(orderBook == null){
            return orderBook;
        }
        orderBook.setStatus(1);
        return orderBookRepository.save(orderBook);
    }

    private OrderBook createOrder(Long idUser, Long idBook, Long idBranch){
        OrderBook orderBook = null;
        UserStatus userStatus = userStatusRepository.findById(idUser).orElseThrow();
        if(userStatus.isStatus() && userStatus.getRemainingbook() > 0){
            userStatus.setRemainingbook(userStatus.getRemainingbook() - 1);
            userStatusRepository.save(userStatus);
            User user = userRepository.findById(idUser).orElseThrow();
            DetailBook detailBook = detailBookRepository.detailBookTop(idBook, idBranch);
            detailBook.setStatus(false);
            DetailBook updateDetail = detailBookRepository.save(detailBook);
            orderBook.setUser(user);
            orderBook.setDetailbook(updateDetail);
            orderBook.setStarttime(new Date(new java.util.Date().getTime()));
            orderBook.setDatetime(new Date(new java.util.Date().getTime() + 120892650*7));

        }
        return orderBook;
    }

    @PutMapping("/getBook/{id}")
    public ResponseEntity<OrderBook> getBook(@PathVariable Long id) {
        OrderBook oderBook = orderBookRepository.findById(id).orElseThrow();
        oderBook.setStatus(2);
        OrderBook updatedOrderBook = orderBookRepository.save(oderBook);
        return ResponseEntity.ok(updatedOrderBook);
    }
    @PutMapping("/giveBack/{id}")
    public ResponseEntity<OrderBook> giveBack(@PathVariable Long id) {
        OrderBook oderBook = orderBookRepository.findById(id).orElseThrow();
        oderBook.setStatus(3);
        oderBook.setEndtime(new Date(new java.util.Date().getTime()));
        OrderBook updatedOrderBook = orderBookRepository.save(oderBook);
        UserStatus userStatus = userStatusRepository.findById(oderBook.getUser().getId()).orElseThrow();
        userStatus.setRemainingbook(userStatus.getRemainingbook() + 1);
        DetailBook detailBook = detailBookRepository.findById(oderBook.getDetailbook().getIddetailbook()).orElseThrow();
        detailBook.setStatus(true);
        detailBookRepository.save(detailBook);
        return ResponseEntity.ok(updatedOrderBook);
    }
}
