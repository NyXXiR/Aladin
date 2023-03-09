package com.project.aladin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;



//default값 설정하려면 해당 column에 @ColumnDefault("") 적용
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@DynamicInsert
@AllArgsConstructor
public class Purchase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long seq;

  private int quantity;
 @DateTimeFormat(pattern="yyyy-MM-dd")
 @CreationTimestamp
 private LocalDateTime buyDate;

  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name="memberSeq")
  private Member member;

  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name="bookSeq")
  private Book book;


}
