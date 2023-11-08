package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.Book;
import com.bezkoder.springjwt.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/book")
    public List <Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @GetMapping("/book/{id}")
    public ResponseEntity< Book > getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(book);
    }
    @PutMapping("/book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity < Book > updateBook(@PathVariable Long id, @RequestBody Book bookDetail) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setBookname(bookDetail.getBookname());
        book.setAuthor(bookDetail.getAuthor());
        book.setPublisher(bookDetail.getPublisher());
        book.setIntroduction(bookDetail.getIntroduction());
        book.setDor(bookDetail.getDor());
        book.setBookimage(bookDetail.getBookimage());
        book.setCodebook(bookDetail.getCodebook());
        book.setCategory(bookDetail.getCategory());

        Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/book/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity <Map< String, Boolean >> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElseThrow();

        bookRepository.delete(book);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/book")
    @PreAuthorize("hasRole('ADMIN')")
    public Book createBook( @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/bookByCategory/{id}")
    public List <Book> getBookByCategory(@PathVariable Long id) {
        return bookRepository.bookByCategory(id);
    }

    @GetMapping("/numberBookByCategory/{id}")
    public Integer getNumberBookByCategory(@PathVariable Long id) {
        return bookRepository.numberBookByCategory(id);
    }
}
