package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.DetailBook;
import com.bezkoder.springjwt.repository.DetailBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class DetailBookController {
    @Autowired
    private DetailBookRepository detailBookRepository;

    @GetMapping("/detailBook")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<DetailBook> getAllDetailBooks() {
        return detailBookRepository.findAll();
    }

    @PostMapping("/detailBook")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DetailBook createDetailBook(@RequestBody DetailBook detailBook) {
        return detailBookRepository.save(detailBook);
    }
    @GetMapping("/detailBook/")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<DetailBook> getDetailBook(@RequestParam Long idBook, @RequestParam Long idBranch) {
        return detailBookRepository.detailBook(idBook,idBranch);
    }
}
