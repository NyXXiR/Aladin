package com.project.aladin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@AllArgsConstructor
@DynamicInsert

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String memberId;
    private String pw;

    //마이페이지 변수
    @ColumnDefault("0")
    private int mileage;
    @ColumnDefault("0")
    private int totalPrice;
    @ColumnDefault("0")
    private int totalBuyCount;

    @OneToMany(mappedBy="member")
    @Builder.Default

    private List<Comment> commentList =new ArrayList<>();

}
