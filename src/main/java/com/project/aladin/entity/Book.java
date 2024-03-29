package com.project.aladin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
  private LocalDate publishDate;
  
  //가격 정보
  private int price;
  @ColumnDefault("10")
  private int discountRate;
  //마일리지 적립율 의미
  @ColumnDefault("5")
  private int mileageRate;
  @ColumnDefault("3000")
  private int shipPrice;

@OneToMany(mappedBy = "book")
@Builder.Default
  private List<Comment> commentList= new ArrayList<>();

  @OneToMany(mappedBy = "book")
  @Builder.Default
  private List<Purchase> purchaseList= new ArrayList<>();

  @OneToMany(mappedBy = "book")
  @Builder.Default
  private List<Review> reviewList= new ArrayList<>();
}
