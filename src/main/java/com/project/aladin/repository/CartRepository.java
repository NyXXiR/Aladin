package com.project.aladin.repository;

import com.project.aladin.entity.Book;
import com.project.aladin.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

}
