package com.bezkoder.springjwt.repository;


import com.bezkoder.springjwt.models.BookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookEntryRepository extends JpaRepository<BookEntry, Long> {
}
