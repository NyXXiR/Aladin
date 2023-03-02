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
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String memberId;
    private String pw;

    //마이페이지 변수
    private int mileage;
    private int totalPrice;
    private int totalBuyCount;

    @OneToMany(mappedBy="memberId")
    private List<Comment> commentList =new ArrayList<>();

}
