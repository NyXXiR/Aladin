package com.project.aladin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;
//default값 설정하려면 해당 column에 @ColumnDefault("") 적용

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  //서적 정보
  private String bookName;
  private String writer;
  private String publisher;
  private String category;

  @DateTimeFormat
  private String publishDate;
  
  //가격 정보
  private int price;
  @ColumnDefault("0")
  private int discountRate;
  //마일리지 적립율 의미
  @ColumnDefault("5")
  private int savingRate;
  @ColumnDefault("0")
  private int shipPrice;

@OneToMany(mappedBy = "bookId")
  private List<Comment> commentList= new ArrayList<>();

  @OneToMany(mappedBy = "bookId")
  private List<Purchase> purchaseList= new ArrayList<>();
}
