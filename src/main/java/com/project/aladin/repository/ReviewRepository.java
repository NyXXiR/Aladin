package com.project.aladin.repository;

import com.project.aladin.entity.Book;
import com.project.aladin.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

}
