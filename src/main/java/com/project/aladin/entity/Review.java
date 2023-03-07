package com.project.aladin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@DynamicInsert
@AllArgsConstructor
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  private double star;

  private String content;
  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name="memberSeq")
  private Member member;

  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name="bookSeq")
  private Book book;


}
