package com.bezkoder.springjwt.repository;


import com.bezkoder.springjwt.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("select r from Rating r where r.book.idbook = ?1 ")
    List<Rating> ratingListByIdBook(Long idBook);
}
