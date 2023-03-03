package com.project.aladin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@AllArgsConstructor
@DynamicInsert

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long seq;
}
