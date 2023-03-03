package com.project.aladin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@DynamicInsert

@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long seq;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member memberId;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="bookId")
    private Book bookId;

    @ColumnDefault("0")
    private int depth;
}
