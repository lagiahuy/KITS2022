package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Rating;
import com.bezkoder.springjwt.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class RatingController {
    @Autowired
    private RatingRepository ratingRepository;

    @GetMapping("/rating/{id}")
    public List<Rating> getRatingByIdBook(@PathVariable Long id) {
        List<Rating> rating = ratingRepository.ratingListByIdBook(id);
        return rating;
    }

    @PostMapping("/rating")
    public Rating createRating(@RequestBody Rating rating) {
        return ratingRepository.save(rating);
    }
}
