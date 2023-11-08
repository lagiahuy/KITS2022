package com.bezkoder.springjwt.models;

import lombok.Data;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "rating")
@Check(constraints = "rt between 0 and 5")
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idrating;

    @ManyToOne
    @JoinColumn(name = "iduser", nullable = false, referencedColumnName="id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idbook", nullable = false, referencedColumnName="idbook")
    private Book book;

    @Column(columnDefinition = "DATE DEFAULT (CURRENT_DATE)")
    private Date dor;

    @Column(columnDefinition = "Text")
    private String comment;

    private Float rt;

}
