package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@Entity
@Table(name = "user",
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  private String phone;

  private String address;

  @Column(columnDefinition = "varchar(255) default 'https://st.quantrimang.com/photos/image/2017/04/08/anh-dai-dien-FB-200.jpg'")
  private String urlimage;

  private String fullname;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  @JsonIgnore
  private List<BookEntry> bookEntryList;

  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Rating> ratingList;

  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  @JsonIgnore
  private List<OrderBook> orderBookList;

  @OneToOne(mappedBy = "user")
  @JsonIgnore
  private UserStatus userStatus;

  public User() {

  }
}
