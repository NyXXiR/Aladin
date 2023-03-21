package com.project.aladin.repository;

import com.project.aladin.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

  Page<Book> findAll(Pageable pageable);

  List<Book> findByBookNameContaining(String keyword);


}
