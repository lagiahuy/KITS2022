package com.bezkoder.springjwt.repository;


import com.bezkoder.springjwt.models.DetailBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailBookRepository extends JpaRepository<DetailBook, Long> {
    @Query(value = "select db from DetailBook db where db.status = true and db.book.idbook = ?1 and db.branch.idbranch = ?2 group by db.book.codebook")
    DetailBook detailBookTop(Long idBook, Long idBranch);

    @Query(value = "select db from DetailBook db where db.status = true and db.book.idbook = ?1 and db.branch.idbranch = ?2")
    List<DetailBook> detailBook(Long idBook, Long idBranch);
}
