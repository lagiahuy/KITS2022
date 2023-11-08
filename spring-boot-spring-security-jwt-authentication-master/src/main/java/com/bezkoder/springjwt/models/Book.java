package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;


@Data
@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idbook;

    @Column(name = "bookname", nullable = false)
    private String bookname;

    private String author;

    private String publisher;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(columnDefinition = "DATE")
    private Date dor;

    @Column(name = "bookimage", columnDefinition = "varchar(255) default 'https://khothietke.net/wp-content/uploads/2021/05/PNGkhothietke.net-02705.png'")
    private String bookimage;

    @Column(nullable = false, unique = true)
    private String codebook;

    @ManyToOne
    @JoinColumn(name = "idcategory", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rating> ratingList;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DetailBook> detailBookList;

}
