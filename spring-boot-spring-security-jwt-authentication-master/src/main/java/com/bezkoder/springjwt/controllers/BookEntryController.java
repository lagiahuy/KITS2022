package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.BookEntry;
import com.bezkoder.springjwt.repository.BookEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class BookEntryController {
    @Autowired
    private BookEntryRepository bookEntryRepository;

    @GetMapping("/bookEntry")
    @PreAuthorize("hasRole('ADMIN')")
    public List<BookEntry> getAllBookEntrys() {
        return bookEntryRepository.findAll();
    }

    @PostMapping("/bookEntry")
    public BookEntry createBookEntry(@RequestBody BookEntry bookEntry) {
        return bookEntryRepository.save(bookEntry);
    }

}
